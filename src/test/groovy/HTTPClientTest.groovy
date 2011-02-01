@Typed
package com.chocolatey.pmsencoder

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import java.lang.NoClassDefFoundError

/*
    prior to Groovy++ 0.4.99, HTTPBuilder (via HTTPClient) was acting flaky under
    MIXED typing. try to pin it down
*/

class HTTPClientTest extends PMSEncoderTestCase {
    private HTTPClient http = new HTTPClient()

    void testHead() {
        assert http.head('http://www.example.com') == true
        assert http.head('http://ps3mediaserver.googlecode.com/nosuchfile.txt') == false
    }

    void testGet() {
        def example = http.get('http://www.example.com')

        assert example != null
        assert example instanceof String
        assert example =~ /RFC\s+2606/
        assert http.get('http://ps3mediaserver.googlecode.com/nosuchfile.txt') == null
    }

    void testTarget() {
        def target = http.target('http://www.sun.com')

        assert target != null
        assert target =~ '\\.oracle\\.com/'
        assert http.target('http://ps3mediaserver.googlecode.com/nosuchfile.txt') == null
    }
}
