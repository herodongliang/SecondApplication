package com.example.administrator.secondapplication.info;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class DetailMessagesInfo {

    /**
     * comment : 你们都是好样的
     * created_at : 1.482907454296167E9
     * dimensions : [{"id":17,"label":"skill","created_at":1481090781,"icon":"http://tw.chinacloudapp.cn:8001/statics/images/default.png","predefined":false,"user_id":20}]
     * id : 1013
     * images : []
     * provider : {"avatar":"","country":"CHINA","created_at":1.482905645792496E9,"department":"Dept05","email":"no5@test.com","id":240,"name":"Test05 Test05"}
     * provider_id : 240
     * recipients : [{"avatar":"","country":"CHINA","created_at":1.482905645792496E9,"department":"Dept05","email":"no5@test.com","id":240,"name":"Test05 Test05"},{"avatar":"","country":"Country","created_at":1.482747820659331E9,"department":"Dept03","email":"no3@test.com","id":166,"name":"Test03 Test03"},{"avatar":"","country":"Country","created_at":1.482747820647871E9,"department":"Dept03","email":"no3@test.com","id":165,"name":"Test03 Test03"},{"avatar":"http://tw.chinacloudapp.cn:8000/statics/images/51d754ebabcd538f38f37f7ebaf23f7e.jpg","country":"Country","created_at":1.482746543867987E9,"department":"Dept02","email":"no2@test.com","id":161,"name":"Test02 Test02"},{"avatar":"http://tw.chinacloudapp.cn:8000/statics/images/1858ea3436b3cf2921fa0464a3a9d7ef.jpg","country":"Country","created_at":1.482746517552125E9,"department":"Dept01","email":"no1@test.com","id":159,"name":"Test01 Test01"}]
     * request_id : null
     * star : 3.0
     */

    private double created_at;
    private int id;
    private ProviderBean provider;
    private int provider_id;
    private Object request_id;
    private double star;
    private List<DimensionsBean> dimensions;
    private List<?> images;
    private List<RecipientsBean> recipients;
    private List<DetailFeedbackBean> detailFeedbackBeen;

    public DetailMessagesInfo( double created_at, List<DimensionsBean> dimensions, List<RecipientsBean> recipients,List<DetailFeedbackBean> detailFeedbackBeen) {
        this.created_at = created_at;
        this.dimensions = dimensions;
        this.recipients = recipients;
        this.detailFeedbackBeen=detailFeedbackBeen;
    }

    @Override
    public String toString() {
        return "DetailMessagesInfo{" +
                ", created_at=" + created_at +
                ", id=" + id +
                ", provider=" + provider +
                ", provider_id=" + provider_id +
                ", request_id=" + request_id +
                ", star=" + star +
                ", dimensions=" + dimensions +
                ", images=" + images +
                ", recipients=" + recipients +
                '}';
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

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public Object getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Object request_id) {
        this.request_id = request_id;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public List<DimensionsBean> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<DimensionsBean> dimensions) {
        this.dimensions = dimensions;
    }

    public List<?> getImages() {
        return images;
    }

    public void setImages(List<?> images) {
        this.images = images;
    }

    public List<RecipientsBean> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<RecipientsBean> recipients) {
        this.recipients = recipients;
    }

    public List<DetailFeedbackBean> getDetailFeedbackBeen() {
        return detailFeedbackBeen;
    }

    public void setDetailFeedbackBeen(List<DetailFeedbackBean> detailFeedbackBeen) {
        this.detailFeedbackBeen = detailFeedbackBeen;
    }

    public static class DetailFeedbackBean{

        /**
         * id : 1
         * comment :
         * created_at : 2016-12-07 14:06:29
         * provider_id : 20
         * recipient_id : 20
         * request:_id : 4
         * updated_at : 2016-12-07 14:06:29
         */

        private int id;
        private String comment;
        private String created_at;
        private int provider_id;
        private int recipient_id;
        private List<DetailFeedbackDimensions> detailFeedbackDimensions;

        public DetailFeedbackBean() {

//            this.comment = comment;
//            this.id = id;
//            this.created_at = created_at;
//            this.provider_id = provider_id;
//            this.recipient_id = recipient_id;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getProvider_id() {
            return provider_id;
        }

        public void setProvider_id(int provider_id) {
            this.provider_id = provider_id;
        }

        public int getRecipient_id() {
            return recipient_id;
        }

        public void setRecipient_id(int recipient_id) {
            this.recipient_id = recipient_id;
        }

        public List<DetailFeedbackDimensions> getDetailFeedbackDimensions() {
            return detailFeedbackDimensions;
        }

        public void setDetailFeedbackDimensions(List<DetailFeedbackDimensions> detailFeedbackDimensions) {
            this.detailFeedbackDimensions = detailFeedbackDimensions;
        }

    }


    public static class ProviderBean {
        @Override
        public String toString() {
            return "ProviderBean{" +
                    "avatar='" + avatar + '\'' +
                    ", country='" + country + '\'' +
                    ", created_at=" + created_at +
                    ", department='" + department + '\'' +
                    ", email='" + email + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * avatar :
         * country : CHINA
         * created_at : 1.482905645792496E9
         * department : Dept05
         * email : no5@test.com
         * id : 240
         * name : Test05 Test05
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
        public DimensionsBean(int id, String label, String icon, int created_at) {
            this.id = id;
            this.label = label;
            this.icon = icon;
            this.created_at = created_at;
        }

        public DimensionsBean( String label, String icon, int id,boolean ischecked, boolean ischanged) {
            this.id = id;
            this.label = label;
            this.icon = icon;
            this.created_at = created_at;
            this.ischecked = ischecked;
            this.ischanged = ischanged;
        }

        public DimensionsBean(boolean ischecked, boolean ischanged) {
            this.ischecked = ischecked;
            this.ischanged = ischanged;
        }

        public DimensionsBean(int id, String comment, int star) {
            this.id = id;
            this.comment = comment;
            this.star = star;
        }


        @Override
        public String toString() {
            return "DimensionsBean{" +
                    "id=" + id +
                    ", label='" + label + '\'' +
                    ", icon='" + icon + '\'' +
                    ", ischecked=" + ischecked +
                    ", ischanged=" + ischanged +
                    ", star=" + star +
                    '}';
        }

        /**
         * id : 17
         * label : skill
         * created_at : 1481090781
         * icon : http://tw.chinacloudapp.cn:8001/statics/images/default.png
         * predefined : false
         * user_id : 20
         */

        private int id;
        private String label;
        private int created_at;
        private String icon;
        private boolean predefined;
        private int user_id;
        private boolean ischecked;
        private boolean ischanged;

        private String comment;
        private int star;


        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public boolean ischecked() {
            return ischecked;
        }

        public void setIschecked(boolean ischecked) {
            this.ischecked = ischecked;
        }

        public boolean ischanged() {
            return ischanged;
        }

        public void setIschanged(boolean ischanged) {
            this.ischanged = ischanged;
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

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isPredefined() {
            return predefined;
        }

        public void setPredefined(boolean predefined) {
            this.predefined = predefined;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }

    public static class RecipientsBean {
        public RecipientsBean(String replied_status,String avatar, String country, double created_at, String department, String email, int id, String name) {
            this.replied_status=replied_status;
            this.avatar = avatar;
            this.country = country;
            this.created_at = created_at;
            this.department = department;
            this.email = email;
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "RecipientsBean{" +
                    "avatar='" + avatar + '\'' +
                    ", country='" + country + '\'' +
                    ", created_at=" + created_at +
                    ", department='" + department + '\'' +
                    ", email='" + email + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * avatar :
         * country : CHINA
         * created_at : 1.482905645792496E9
         * department : Dept05
         * email : no5@test.com
         * id : 240
         * name : Test05 Test05
         */

        private String avatar;
        private String country;
        private double created_at;
        private String department;
        private String email;
        private int id;
        private String name;
        private String replied_status;

        public String getReplied_status() {
            return replied_status;
        }

        public void setReplied_status(String replied_status) {
            this.replied_status = replied_status;
        }

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
}
