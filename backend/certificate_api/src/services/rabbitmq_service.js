const amqp = require('amqplib/callback_api');
const config = require('../../configs/config');
const {RABBITMQ_SERVER} = require('../../configs/config');
const EVENTS_TOPIC = config.EVENTS_TOPIC;
const DEFAULT_ROUTING_KEY = "";

var isConnecting = false;
var amqpConn = null;
var pubChannel = null;

async function initRabbitmq() {
  if (isConnecting)
    return;
  isConnecting = true;
  amqp.connect(RABBITMQ_SERVER + "?heartbeat=60", function(err, conn) {
    isConnecting = false;
    if (err) {
      console.error("[AMQP]", err.message);
      return setTimeout(initRabbitmq, 1000);
    }
    conn.on("error", function(err) {
      if (err.message !== "Connection closing") {
        console.error("[AMQP] conn error", err.message);
      }
    });
    conn.on("close", function() {
      console.error("[AMQP] reconnecting");
      return setTimeout(initRabbitmq, 1000);
    });
    console.log("[AMQP] connected");
    amqpConn = conn;
    whenConnected();
  });
}

function whenConnected() {
  startPublisher();
}

function startPublisher() {
  amqpConn.createConfirmChannel(function(err, ch) {
    if (closeOnErr(err)) return;
    ch.on("error", function(err) {
      console.error("[AMQP] channel error", err.message);
    });
    ch.on("close", function() {
      console.log("[AMQP] channel closed");
    });
    pubChannel = ch;
  });
}

// method to publish a message, will queue messages internally if the connection is down and resend later
function publish(exchange, routingKey, content) {
  try {
    pubChannel.publish(exchange, routingKey, content,
       { persistent: true },
         function(err, ok) {
           closeOnErr(err);
         });
  } catch (e) {
    console.error("[AMQP] publish", e.message);
  }
}

function sendEventsViaRabbitmq(event) {
  var msg = [{key: null, value: JSON.stringify(event)}];
  publish(EVENTS_TOPIC, DEFAULT_ROUTING_KEY, Buffer.from(JSON.stringify(msg)));
}

function closeOnErr(err) {
  if (!err) return false;
  console.error("[AMQP] error", err);
  amqpConn.close();
  return true;
}

module.exports = {
    initRabbitmq,
    sendEventsViaRabbitmq
};
