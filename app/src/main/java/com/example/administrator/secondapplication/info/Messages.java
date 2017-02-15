package com.example.administrator.secondapplication.info;

/**
 * Created by guadong on 11/24/2016.
 */
public class Messages {
//    public String username;
//    public String userimage;
//    public String team;
//    public String timemesssage;
//    public String content;

    public Messages() {
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", read=" + read +
                ", entity_id=" + entity_id +
                ", entity_type='" + entity_type + '\'' +
                ", stake_holder=" + stake_holder +
                ", user_id=" + user_id +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    /**
     * id : 2
     * "content": "invites you to give her/him a feedback"
     * read : true
     * entity_id : 4
     * entity_type : FeedbackRequest
     * stake_holder : {"country":"China","created_at":1481090777,"department":"dp6","email":"liuyalin@360feedback.com","id":20,"name":"Yalin Liu"}
     * user_id : 20
     * created_at : 2016-12-07 14:06:25
     */

    private int id;
    private String content;
    private boolean read;
    private int entity_id;
    private String entity_type;
    private StakeHolderBean stake_holder;
    private int user_id;
    private Double created_at;


    public Messages(int id,String content, boolean read, int entity_id, StakeHolderBean stake_holder, int user_id, Double created_at, String entity_type) {
        this.id = id;
        this.content=content;
        this.read = read;
        this.entity_id = entity_id;
        this.stake_holder = stake_holder;
        this.user_id = user_id;
        this.created_at = created_at;
        this.entity_type = entity_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public StakeHolderBean getStake_holder() {
        return stake_holder;
    }

    public void setStake_holder(StakeHolderBean stake_holder) {
        this.stake_holder = stake_holder;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Double getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Double created_at) {
        this.created_at = created_at;
    }

    public static class StakeHolderBean {
        public StakeHolderBean(String country, int created_at, String department, String email, int id, String name,String avatar) {
            this.country = country;
            this.created_at = created_at;
            this.department = department;
            this.email = email;
            this.id = id;
            this.name = name;
            this.avatar=avatar;
        }

        @Override
        public String toString() {
            return "StakeHolderBean{" +
                    "country='" + country + '\'' +
                    ", created_at=" + created_at +
                    ", department='" + department + '\'' +
                    ", email='" + email + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * country : China
         * created_at : 1481090777
         * department : dp6
         * email : liuyalin@360feedback.com
         * id : 20
         * name : Yalin Liu
         */

        private String country;
        private int created_at;
        private String department;
        private String email;
        private int id;
        private String name;
        private String avatar;

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

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
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
