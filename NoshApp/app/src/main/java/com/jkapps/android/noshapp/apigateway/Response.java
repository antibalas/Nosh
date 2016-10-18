package com.jkapps.android.noshapp.apigateway;

/*  This is just some starter code for our Response class.
    These won't be all the fields we want to use (and we
    won't want to use a body field), but we can modify the
    fields here pretty easily.
 */
public class Response {

    private final String statusCode;
    private final String body;
    private final String headers;

    private Response(final Builder builder) {
        this.statusCode = builder.statusCode;
        this.body       = builder.body;
        this.headers    = builder.headers;
    }

    public String getStatusCode() { return statusCode; }

    public String getBody() { return body; }

    public String getHeaders() { return headers; }

    //we can use the builder pattern here
    public static class Builder {

        private String statusCode;
        private String body;
        private String headers;

        public Builder withStatusCode(final String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder withBody(final String body) {
            this.body = body;
            return this;
        }

        public Builder withHeaders(final String headers) {
            this.headers = headers;
            return this;
        }

        public Response build() { return new Response(this); }
    }
}
