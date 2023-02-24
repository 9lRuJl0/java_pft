package ru.stqa.pft.soap;

import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public class GeoIpServiceTests {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("37.204.248.185");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("37.204.248.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
