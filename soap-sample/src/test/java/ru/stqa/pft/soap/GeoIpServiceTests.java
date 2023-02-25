package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.204.248.185");

        assertEquals(geoIp,"<GeoIP><Country>RU</Country><State>53</State></GeoIP>" );
        System.out.println(geoIp);
    }



    @Test
    public void testInvalidIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("xxx.204.248.xx");

        assertEquals(geoIp,"<GeoIP><Country>RU</Country><State>53</State></GeoIP>" );
        System.out.println(geoIp);

    }
}