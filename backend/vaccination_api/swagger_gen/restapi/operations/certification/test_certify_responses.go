// Code generated by go-swagger; DO NOT EDIT.

package certification

// This file was generated by the swagger tool.
// Editing this file might prove futile when you re-run the swagger generate command

import (
	"net/http"

	"github.com/go-openapi/runtime"

	"github.com/divoc/api/swagger_gen/models"
)

// TestCertifyOKCode is the HTTP code returned for type TestCertifyOK
const TestCertifyOKCode int = 200

/*TestCertifyOK OK

swagger:response testCertifyOK
*/
type TestCertifyOK struct {
}

// NewTestCertifyOK creates TestCertifyOK with default headers values
func NewTestCertifyOK() *TestCertifyOK {

	return &TestCertifyOK{}
}

// WriteResponse to the client
func (o *TestCertifyOK) WriteResponse(rw http.ResponseWriter, producer runtime.Producer) {

	rw.Header().Del(runtime.HeaderContentType) //Remove Content-Type on empty responses

	rw.WriteHeader(200)
}

// TestCertifyBadRequestCode is the HTTP code returned for type TestCertifyBadRequest
const TestCertifyBadRequestCode int = 400

/*TestCertifyBadRequest Invalid input

swagger:response testCertifyBadRequest
*/
type TestCertifyBadRequest struct {

	/*
	  In: Body
	*/
	Payload *models.Error `json:"body,omitempty"`
}

// NewTestCertifyBadRequest creates TestCertifyBadRequest with default headers values
func NewTestCertifyBadRequest() *TestCertifyBadRequest {

	return &TestCertifyBadRequest{}
}

// WithPayload adds the payload to the test certify bad request response
func (o *TestCertifyBadRequest) WithPayload(payload *models.Error) *TestCertifyBadRequest {
	o.Payload = payload
	return o
}

// SetPayload sets the payload to the test certify bad request response
func (o *TestCertifyBadRequest) SetPayload(payload *models.Error) {
	o.Payload = payload
}

// WriteResponse to the client
func (o *TestCertifyBadRequest) WriteResponse(rw http.ResponseWriter, producer runtime.Producer) {

	rw.WriteHeader(400)
	if o.Payload != nil {
		payload := o.Payload
		if err := producer.Produce(rw, payload); err != nil {
			panic(err) // let the recovery middleware deal with this
		}
	}
}
