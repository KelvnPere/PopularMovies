package com.fashi.popularmovies;


import java.util.List;

public class VideoClass {

    /**
     * id : 131634
     * results : [{"id":"571cc2b5c3a36842aa0009e8","iso_639_1":"en","iso_3166_1":"US","key":"YddkQoxkZMQ","name":"The Hunger Games: Mockingjay Part 2 Official Trailer \u2013 \u201cFor Prim\u201d","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571cc2919251414a87001591","iso_639_1":"en","iso_3166_1":"US","key":"n-7K_OjsDCQ","name":"The Hunger Games: Mockingjay Part 2 Official Trailer \u2013 \u201cWe March Together\u201d","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571cc2d2c3a36838cd00147e","iso_639_1":"en","iso_3166_1":"US","key":"K9Qc4FS2VB0","name":"The Hunger Games: Mockingjay Part 2 Official Clip \u2013 \u201cStar Squad\u201d","site":"YouTube","size":720,"type":"Clip"},{"id":"571cc2f692514135ff0045ba","iso_639_1":"en","iso_3166_1":"US","key":"Zk3yLI0q794","name":"The Hunger Games: Mockingjay Part 2 Official Clip \u2013 \u201cReal\u201d","site":"YouTube","size":1080,"type":"Clip"}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 571cc2b5c3a36842aa0009e8
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : YddkQoxkZMQ
         * name : The Hunger Games: Mockingjay Part 2 Official Trailer – “For Prim”
         * site : YouTube
         * size : 1080
         * type : Trailer
         */

        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}