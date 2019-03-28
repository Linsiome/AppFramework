package com.aolan.b365.mvp.model.entity;

/**
 * Created by Administrator on 2017/3/28.
 */
public class LoginInfo {
    /**
     * status : success
     * code : 200
     * data : {"token_type":"Bearer","expires_in":1295999,"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM3YTA5MjY1MzQ0Yzg1NmJmMzFjNTY3OTFiZmU5ZjA1NDdlZTdjMDBhOTQwNjljMWM0ZTlkMWYwNDVkZTRkNDVhMWJiZTI2ZTkxMzM5ZjFhIn0.eyJhdWQiOiIyIiwianRpIjoiYzdhMDkyNjUzNDRjODU2YmYzMWM1Njc5MWJmZTlmMDU0N2VlN2MwMGE5NDA2OWMxYzRlOWQxZjA0NWRlNGQ0NWExYmJlMjZlOTEzMzlmMWEiLCJpYXQiOjE1MzI2NTk3OTUsIm5iZiI6MTUzMjY1OTc5NSwiZXhwIjoxNTMzOTU1Nzk0LCJzdWIiOiIyOCIsInNjb3BlcyI6WyIqIl19.VBSz4nfFqQKLrn6MfMK6u_i5GvXP7cLYhaOJVEhpPMEakHEXdE4cKZTleJ_n1GnW8H5disTy1j5fS05kniu1lfYqBynQzYn1pPaFU-5q6eTo-wRE6AjMOBgZez9wmjIFDR4k2yByDiXIRjc_FOAzMkfvkwmVuDvSfr8yEmoERslIcjnxDVd7zf0lP1n9EZzdbCK2G1F6HhDiArjoEhtXTf9pBiudVV13UO9o898nezidsDfhyCf5QO0yg4oGKjTyh6-P7Ba3b9nAB2IslbvJcIltC0Q6MGqwHtgZPfwUNB31O_iCacju3u2nClSjuOdBQFUPl1g1A5p_fcF0IfuKKq_RKR_1vSdBRxJG4R_D6yCpRySSndRVutvQV9v7psYyupdxTkDK0Qx4ZIgIu8vFubqP7p4O0z5L4wWpqmVKlctsGDJ4Gi0TJ-mxRQcAUQXmOzLLCGbwhhJUXxpLjmFTsalnUvbEx0CDCoczvOkLiMXcKdVd70Ex7YkLUEfguWj3IMvgcLfanrF1YChmupzrrXQn6emHSNokL1d_LnjUvpwgRAdFU76_5EVFEyr_sGeepABG-Rj5uYVCL4ThdpYyc6O6uSIkfmZf7rgafsMEhRsLLiUOLSEYLyMyp2p919EVAXsfpGab5msQVVaZPLzyWy3Ym-jRVQtWECcRPSieP8A","refresh_token":"def502004371e0515bf4be0361e70f68960c16251143e8655aebcfdb3765ef7d408fc2d62269968043375b8a5c3fe662eaf6734cacc7875517c93ac77f8557e1bc1803a8024c69fa4d4937c7a94c1843aa2dd34bb49acca923e6b6cb700143a4a8289c2a4ef5b9900f8a17d2a57b05b623ba4120e2735f042a63607a31c7e37af5207014e96913b192ff5305347ecfc544a4057657eefd35a7c4251cadd3323c030f2b88477280bd2603c7dbaeeaa87819b5d6890dcd024c26d61bb182ec9ac604a6662ca384fbf115daa5543c398c3cbc948ae8cc087346950fbe374f4e3e2c70e03841308873f365da20d6d2d1124fa97d4fb70b7c9bed8a6f51a7b484581897a550b57e09f4cc34132d7a80bbf4fa2a30738f921cf1e173352da45fb69cf6809258a89181343a7cb0614284c730d460f95ab133c9ff306493262730af6117e11d6af724e8dc70e2fd593df8f1a0f88eaa22a13ffd51d4e346ad11181f0e604256efe548"}
     */

    private String status;
    private int code;
    private DataBean data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token_type : Bearer
         * expires_in : 1295999
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM3YTA5MjY1MzQ0Yzg1NmJmMzFjNTY3OTFiZmU5ZjA1NDdlZTdjMDBhOTQwNjljMWM0ZTlkMWYwNDVkZTRkNDVhMWJiZTI2ZTkxMzM5ZjFhIn0.eyJhdWQiOiIyIiwianRpIjoiYzdhMDkyNjUzNDRjODU2YmYzMWM1Njc5MWJmZTlmMDU0N2VlN2MwMGE5NDA2OWMxYzRlOWQxZjA0NWRlNGQ0NWExYmJlMjZlOTEzMzlmMWEiLCJpYXQiOjE1MzI2NTk3OTUsIm5iZiI6MTUzMjY1OTc5NSwiZXhwIjoxNTMzOTU1Nzk0LCJzdWIiOiIyOCIsInNjb3BlcyI6WyIqIl19.VBSz4nfFqQKLrn6MfMK6u_i5GvXP7cLYhaOJVEhpPMEakHEXdE4cKZTleJ_n1GnW8H5disTy1j5fS05kniu1lfYqBynQzYn1pPaFU-5q6eTo-wRE6AjMOBgZez9wmjIFDR4k2yByDiXIRjc_FOAzMkfvkwmVuDvSfr8yEmoERslIcjnxDVd7zf0lP1n9EZzdbCK2G1F6HhDiArjoEhtXTf9pBiudVV13UO9o898nezidsDfhyCf5QO0yg4oGKjTyh6-P7Ba3b9nAB2IslbvJcIltC0Q6MGqwHtgZPfwUNB31O_iCacju3u2nClSjuOdBQFUPl1g1A5p_fcF0IfuKKq_RKR_1vSdBRxJG4R_D6yCpRySSndRVutvQV9v7psYyupdxTkDK0Qx4ZIgIu8vFubqP7p4O0z5L4wWpqmVKlctsGDJ4Gi0TJ-mxRQcAUQXmOzLLCGbwhhJUXxpLjmFTsalnUvbEx0CDCoczvOkLiMXcKdVd70Ex7YkLUEfguWj3IMvgcLfanrF1YChmupzrrXQn6emHSNokL1d_LnjUvpwgRAdFU76_5EVFEyr_sGeepABG-Rj5uYVCL4ThdpYyc6O6uSIkfmZf7rgafsMEhRsLLiUOLSEYLyMyp2p919EVAXsfpGab5msQVVaZPLzyWy3Ym-jRVQtWECcRPSieP8A
         * refresh_token : def502004371e0515bf4be0361e70f68960c16251143e8655aebcfdb3765ef7d408fc2d62269968043375b8a5c3fe662eaf6734cacc7875517c93ac77f8557e1bc1803a8024c69fa4d4937c7a94c1843aa2dd34bb49acca923e6b6cb700143a4a8289c2a4ef5b9900f8a17d2a57b05b623ba4120e2735f042a63607a31c7e37af5207014e96913b192ff5305347ecfc544a4057657eefd35a7c4251cadd3323c030f2b88477280bd2603c7dbaeeaa87819b5d6890dcd024c26d61bb182ec9ac604a6662ca384fbf115daa5543c398c3cbc948ae8cc087346950fbe374f4e3e2c70e03841308873f365da20d6d2d1124fa97d4fb70b7c9bed8a6f51a7b484581897a550b57e09f4cc34132d7a80bbf4fa2a30738f921cf1e173352da45fb69cf6809258a89181343a7cb0614284c730d460f95ab133c9ff306493262730af6117e11d6af724e8dc70e2fd593df8f1a0f88eaa22a13ffd51d4e346ad11181f0e604256efe548
         */

        private String token_type;
        private int expires_in;
        private String access_token;
        private String refresh_token;

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }

    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjE0LCJpc3MiOiJodHRwOi8venMuYjM2NWkuY29tL2luZGV4LnBocC9hcGkvdXNlci9maXhlZF9sb2dpbiIsImlhdCI6MTUxMDU1NTc2MiwiZXhwIjoxNTEwNTU5MzYyLCJuYmYiOjE1MTA1NTU3NjIsImp0aSI6IklqWWQ3eVJZMTZGYkhIRjYifQ.p1hQRxP5mghQkNlcd5ozz3YyhIhvM4kJDybeCjDFfOo
     * status_code : 200
     *
     */


}
