package com.example.administrator.secondapplication.info;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
public class DetailFeedbackDimensions {


    /**
     * comment :
     * created_at : 1.482308232100253E9
     * dimensions : [{"comment":"bbvb","created_at":1.482308232100253E9,"evaluative_dimension_id":1,"feedback_id":181,"icon":"http://tw.chinacloudapp.cn:8001/statics/images/content.png","id":172,"label":"content","star":3},{"comment":" bbbb","created_at":1.482308232100253E9,"evaluative_dimension_id":27,"feedback_id":181,"icon":"http://tw.chinacloudapp.cn:8001/statics/images/default.png","id":173,"label":"ssssss","star":2}]
     * id : 181
     * provider : {"avatar":"","country":"vbhhh","created_at":1.482307226028367E9,"department":"gbbbsjjanna","email":"MeiYou@test.com","id":97,"name":"Qpp Hh"}
     * provider_id : 97
     * request_id : 180
     * star : null
     */
    private String entity_type;
    private String comment;
    private double created_at;
    private int id;
    private FeedbackProviderBean provider;
    private int provider_id;
    private int request_id;
    private Double star;
    private List<DimensionsBean> dimensions;

    @Override
    public String toString() {
        return "DetailFeedbackDimensions{" +
                "entity_type='" + entity_type + '\'' +
                ", comment='" + comment + '\'' +
                ", created_at=" + created_at +
                ", id=" + id +
                ", provider=" + provider +
                ", provider_id=" + provider_id +
                ", request_id=" + request_id +
                ", star=" + star +
                ", dimensions=" + dimensions +
                '}';
    }

    public DetailFeedbackDimensions(List<DimensionsBean> dimensions, double created_at, String comment, FeedbackProviderBean provider, Double star) {
        this.entity_type=entity_type;
        this.dimensions=dimensions;
        this.created_at = created_at;
        this.comment = comment;
        this.provider = provider;
        this.star =star;
    }
    public DetailFeedbackDimensions(double created_at, String comment, FeedbackProviderBean provider,Double star) {
        this.created_at = created_at;
        this.comment = comment;
        this.provider = provider;
        this.star =star;
    }
    public DetailFeedbackDimensions(String comment, double created_at, Double star) {
        this.comment = comment;
        this.created_at = created_at;
        this.star = star;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getCreated_at() {
        return created_at;
    }

    public void setCreated_at(double created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedbackProviderBean getProvider() {
        return provider;
    }

    public void setProvider(FeedbackProviderBean provider) {
        this.provider = provider;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public List<DimensionsBean> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<DimensionsBean> dimensions) {
        this.dimensions = dimensions;
    }

    public static class FeedbackProviderBean {
        public FeedbackProviderBean(String avatar, double created_at, String department, String name) {
            this.avatar = avatar;
            this.created_at = created_at;
            this.department = department;
            this.name = name;
        }

        /**
         * avatar :
         * country : vbhhh
         * created_at : 1.482307226028367E9
         * department : gbbbsjjanna
         * email : MeiYou@test.com
         * id : 97
         * name : Qpp Hh
         */

        private String avatar;
        private String country;
        private double created_at;
        private String department;
        private String email;
        private int id;
        private String name;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getCreated_at() {
            return created_at;
        }

        public void setCreated_at(double created_at) {
            this.created_at = created_at;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static class DimensionsBean {
        public DimensionsBean(String comment, String icon, String label, Double star, double created_at) {
            this.comment = comment;
            this.icon = icon;
            this.label = label;
            this.star = star;
            this.created_at = created_at;
        }

        public DimensionsBean() {
        }

        @Override
        public String toString() {
            return "DimensionsBean{" +
                    "comment='" + comment + '\'' +
                    ", icon='" + icon + '\'' +
                    ", label='" + label + '\'' +
                    ", star=" + star +
                    ", created_at=" + created_at +
                    '}';
        }

        /**
         * comment : bbvb
         * created_at : 1.482308232100253E9
         * evaluative_dimension_id : 1
         * feedback_id : 181
         * icon : http://tw.chinacloudapp.cn:8001/statics/images/content.png
         * id : 172
         * label : content
         * star : 3
         */

        private String comment;
        private double created_at;
        private int evaluative_dimension_id;
        private int feedback_id;
        private String icon;
        private int id;
        private String label;
        private Double star;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public double getCreated_at() {
            return created_at;
        }

        public void setCreated_at(double created_at) {
            this.created_at = created_at;
        }

        public int getEvaluative_dimension_id() {
            return evaluative_dimension_id;
        }

        public void setEvaluative_dimension_id(int evaluative_dimension_id) {
            this.evaluative_dimension_id = evaluative_dimension_id;
        }

        public int getFeedback_id() {
            return feedback_id;
        }

        public void setFeedback_id(int feedback_id) {
            this.feedback_id = feedback_id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Double getStar() {
            return star;
        }

        public void setStar(Double star) {
            this.star = star;
        }
    }
}
