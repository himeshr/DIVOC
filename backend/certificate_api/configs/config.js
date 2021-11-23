const REGISTRY_URL = process.env.REGISTRY_URL || 'http://0.0.0.0:8081';
const KEYCLOAK_URL = process.env.KEYCLOAK_URL || 'http://keycloak:8080/auth';
const KEYCLOAK_REALM = 'divoc';
const JWT_PUBLIC_KEY = process.env.AUTH_PUBLIC_KEY ||
`-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1mIX9SFluySnfIfT6Jz5
LdORWO+O0j3L7cF+BLaAdX7JuiN10MTISpyOA5z81YXMSgk0PPLUzHv5DTDPMR3r
sb6ut/PweLn9sLmwsj9OGSAuH7DFOHVV+Mct9TXBeZu1tzboDhtqKz1PMnBFdmgv
d2+WnKYtdWfgo8kB82WkGolAT7++efm/HnOkvRkoNbefbbpnELjtcM5uN32HdCpz
Tm87zshvOoEvkBUcBJdmKNjzLwBc0E2SpthOuxQUw7zSSqnvOOW+sOub6dIgDgR5
57KyVGKL9NUvlbbp9jglcZERtpE6pRhbjDwBlmzx8NufSap24VzWIUNjy+CiF0J6
OQIDAQAB
-----END PUBLIC KEY-----`;
const KAFKA_BOOTSTRAP_SERVER = process.env.KAFKA_BOOTSTRAP_SERVERS || 'kafka:9092';
const RABBITMQ_SERVER = process.env.RABBITMQ_SERVER || 'rabbitmq:5672';
const EVENTS_TOPIC = 'events';
const COMMUNICATION_MODE_KAFKA = 'kafka';
const COMMUNICATION_MODE_RABBITMQ = 'rabbitmq';
const COMMUNICATION_MODE_RESTAPI = 'restapi';
const COMMUNICATION_MODE = process.env.COMMUNICATION_MODE || 'rabbitmq';

const DISEASE_CODE = process.env.DISEASE_CODE || 'COVID-19';
const PUBLIC_HEALTH_AUTHORITY = process.env.FHIR_PUBLIC_HEALTH_AUTHORITY || 'Govt Of India';
const EU_CERTIFICATE_EXPIRY = parseInt(process.env.EU_CERTIFICATE_EXPIRY) || 12;
module.exports = {
    REGISTRY_URL,
    JWT_PUBLIC_KEY,
    KAFKA_BOOTSTRAP_SERVER,
    RABBITMQ_SERVER,
    EVENTS_TOPIC,
    KEYCLOAK_URL,
    KEYCLOAK_REALM,
    COMMUNICATION_MODE_KAFKA,
    COMMUNICATION_MODE_RABBITMQ,
    COMMUNICATION_MODE_RESTAPI,
    COMMUNICATION_MODE,
    DISEASE_CODE,
    PUBLIC_HEALTH_AUTHORITY,
    EU_CERTIFICATE_EXPIRY
};
