package com.example.administrator.secondapplication.model;



import com.example.administrator.secondapplication.bean.DetailFeedbackDimensions;
import com.example.administrator.secondapplication.lrecyclertext.MultiItemEntity;

import java.util.List;

/**
 * Created by DavidWang on 15/8/31.
 */
public class ImageInfo implements MultiItemEntity {

    /**
     * comment_count : 10
     * created_at : 1.48227697052076E9
     * description : Invites 2 people for feedback
     * entity_id : 158
     * entity_type : FeedbackRequest
     * feedback : {}
     * feedback_request : {"description":"哄哄"}
     * id : 117
     * likes : 100
     * nice_one : {}
     * page_views : 100
     * sponsor : {"country":null,"created_at":1.482215947986889E9,"department":"ITS/CD","email":"lkiop@qq.com","id":9,"name":"lkiop"}
     * sponsor_id : 9
     * updated_at : 1.48227697052076E9
     */
    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int comment_count;
    private double created_at;
    private String description;
    private int entity_id;
    private String entity_type;
    private FeedbackBean feedback;
    private FeedbackRequestBean feedback_request;
    private int id;
    private int likes;
    private NiceOneBean nice_one;
    private int page_views;
    private SponsorBean sponsor;
    private int sponsor_id;
    private double updated_at;
    private int type;
    private List<DetailFeedbackDimensions.DimensionsBean> listdimesion;
    private List<String> stringList;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<DetailFeedbackDimensions.DimensionsBean> getListdimesion() {
        return listdimesion;
    }

    public void setListdimesion(List<DetailFeedbackDimensions.DimensionsBean> listdimesion) {
        this.listdimesion = listdimesion;
    }

    public ImageInfo(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ImageInfo() {
    }

    public ImageInfo(String description, int page_views, SponsorBean sponsor, int likes, FeedbackBean feedback) {
        this.description = description;
        this.page_views = page_views;
        this.sponsor = sponsor;
        this.likes = likes;
        this.feedback = feedback;
    }

    public ImageInfo(List<String> stringList,List<DetailFeedbackDimensions.DimensionsBean> listdimesion,int comment_count, double created_at, String description, String entity_type, FeedbackBean feedback, FeedbackRequestBean feedback_request, int id, int likes, NiceOneBean nice_one, int page_views, SponsorBean sponsor, int sponsor_id, double updated_at, int type, int entity_id) {
        this.stringList=stringList;
        this.listdimesion=listdimesion;
        this.comment_count = comment_count;
        this.created_at = created_at;
        this.description = description;
        this.entity_type = entity_type;
        this.feedback = feedback;
        this.feedback_request = feedback_request;
        this.id = id;
        this.likes = likes;
        this.nice_one = nice_one;
        this.page_views = page_views;
        this.sponsor = sponsor;
        this.sponsor_id = sponsor_id;
        this.updated_at = updated_at;
        this.type = type;
        this.entity_id = entity_id;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "comment_count=" + comment_count +
                ", created_at=" + created_at +
                ", description='" + description + '\'' +
                ", entity_id=" + entity_id +
                ", entity_type='" + entity_type + '\'' +
                ", feedback=" + feedback +
                ", feedback_request=" + feedback_request +
                ", id=" + id +
                ", likes=" + likes +
                ", nice_one=" + nice_one +
                ", page_views=" + page_views +
                ", sponsor=" + sponsor +
                ", sponsor_id=" + sponsor_id +
                ", updated_at=" + updated_at +
                ", type=" + type +
                '}';
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public double getCreated_at() {
        return created_at;
    }

    public void setCreated_at(double created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public FeedbackBean getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackBean feedback) {
        this.feedback = feedback;
    }

    public FeedbackRequestBean getFeedback_request() {
        return feedback_request;
    }

    public void setFeedback_request(FeedbackRequestBean feedback_request) {
        this.feedback_request = feedback_request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public NiceOneBean getNice_one() {
        return nice_one;
    }

    public void setNice_one(NiceOneBean nice_one) {
        this.nice_one = nice_one;
    }

    public int getPage_views() {
        return page_views;
    }

    public void setPage_views(int page_views) {
        this.page_views = page_views;
    }

    public SponsorBean getSponsor() {
        return sponsor;
    }

    public void setSponsor(SponsorBean sponsor) {
        this.sponsor = sponsor;
    }

    public int getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(int sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public double getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(double updated_at) {
        this.updated_at = updated_at;
    }



    @Override
    public int getItemType() {
        return type;
    }


    public static class FeedbackBean {
        public FeedbackBean(String comment, int star, List<DimensionsBean> dimensions, List<ImagesBean> images) {
            this.comment = comment;
            this.star = star;
            this.dimensions = dimensions;
            this.images = images;
        }

        @Override
        public String toString() {
            return "FeedbackBean{" +
                    "comment='" + comment + '\'' +
                    ", star=" + star +
                    ", dimensions=" + dimensions +
                    ", images=" + images +
                    '}';
        }

        /**
         * comment : 123
         * dimensions : [{"comment":"？？","created_at":1.484726868341908E9,"evaluative_dimension_id":53,"feedback_id":2153,"icon":"http://tw.chinacloudapp.cn:8001/statics/images/communication.png","id":1084,"label":"Teamwork","star":3}]
         * images : [{"attachable_id":2152,"attachable_type":null,"created_at":1.484724166269027E9,"id":554,"image_type":"image/png","name":"1511f2d6b2970805951ae794f8af49e4.jpg","size":766397,"updated_at":1.48472416652825E9,"url":"http://tw.chinacloudapp.cn:8001/statics/images/1511f2d6b2970805951ae794f8af49e4.jpg"},{"attachable_id":2152,"attachable_type":null,"created_at":1.484724166269027E9,"id":555,"image_type":"image/png","name":"ec4d2e6658a197281fc91a45220efd17.jpg","size":706510,"updated_at":1.48472416652825E9,"url":"http://tw.chinacloudapp.cn:8001/statics/images/ec4d2e6658a197281fc91a45220efd17.jpg"},{"attachable_id":2152,"attachable_type":null,"created_at":1.484724166269027E9,"id":556,"image_type":"image/png","name":"da960baa923ba20ece9ca7c74a5b0b30.jpg","size":652405,"updated_at":1.48472416652825E9,"url":"http://tw.chinacloudapp.cn:8001/statics/images/da960baa923ba20ece9ca7c74a5b0b30.jpg"},{"attachable_id":2152,"attachable_type":null,"created_at":1.484724166269027E9,"id":557,"image_type":"image/png","name":"6f1724cac8d0ec0698f4d966fc017bc7.jpg","size":692439,"updated_at":1.48472416652825E9,"url":"http://tw.chinacloudapp.cn:8001/statics/images/6f1724cac8d0ec0698f4d966fc017bc7.jpg"}]
         * star : 3
         */



        private String comment;
        private int star;
        private List<DimensionsBean> dimensions;
        private List<ImagesBean> images;

        public FeedbackBean() {
        }

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

        public List<DimensionsBean> getDimensions() {
            return dimensions;
        }

        public void setDimensions(List<DimensionsBean> dimensions) {
            this.dimensions = dimensions;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }


        public static class DimensionsBean {
            public DimensionsBean(String comment, String icon, String label, int star) {
                this.comment = comment;
                this.icon = icon;
                this.label = label;
                this.star = star;
            }

            @Override
            public String toString() {
                return "DimensionsBean{" +
                        "comment='" + comment + '\'' +
                        ", icon='" + icon + '\'' +
                        ", label='" + label + '\'' +
                        ", star=" + star +
                        '}';
            }

            public DimensionsBean() {
            }

            /**
             * comment : ？？
             * created_at : 1.484726868341908E9
             * evaluative_dimension_id : 53
             * feedback_id : 2153
             * icon : http://tw.chinacloudapp.cn:8001/statics/images/communication.png
             * id : 1084
             * label : Teamwork
             * star : 3
             */

            private String comment;
            private String icon;
            private String label;
            private int star;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }
        }

        public static class ImagesBean {
            public ImagesBean(int id, String url) {
                this.id = id;
                this.url = url;
            }

            @Override
            public String toString() {
                return "ImagesBean{" +
                        "id=" + id +
                        ", url='" + url + '\'' +
                        '}';
            }

            public ImagesBean() {
            }

            /**
             * attachable_id : 2152
             * attachable_type : null
             * created_at : 1.484724166269027E9
             * id : 554
             * image_type : image/png
             * name : 1511f2d6b2970805951ae794f8af49e4.jpg
             * size : 766397
             * updated_at : 1.48472416652825E9
             * url : http://tw.chinacloudapp.cn:8001/statics/images/1511f2d6b2970805951ae794f8af49e4.jpg
             */

            private int id;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class FeedbackRequestBean {
        @Override
        public String toString() {
            return "FeedbackRequestBean{" +
                    "description='" + description + '\'' +
                    '}';
        }

        public FeedbackRequestBean(String description) {
            this.description = description;
        }

        public FeedbackRequestBean() {
        }

        /**
         * description : Chat
         */

        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class NiceOneBean {

        /**
         * created_at : 1.482239185589604E9
         * id : 156
         * label : [{"id":6,"name":"Digital transformation"},{"id":9,"name":"Best fit"}]
         * provider : {"department":"ITS/CD","id":72,"name":"Johannes.knoof"}
         */

        private double created_at;
        private int id;
        private ProviderBean provider;
        private List<LabelBean> label;

        public NiceOneBean(double created_at, int id, ProviderBean provider, List<LabelBean> label) {
            this.created_at = created_at;
            this.id = id;
            this.provider = provider;
            this.label = label;
        }

        public NiceOneBean() {
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

        public List<LabelBean> getLabel() {
            return label;
        }

        public void setLabel(List<LabelBean> label) {
            this.label = label;
        }

        public static class ProviderBean {
            public ProviderBean(String department, int id, String name) {
                this.department = department;
                this.id = id;
                this.name = name;
            }

            /**
             * department : ITS/CD
             * id : 72
             * name : Johannes.knoof
             */

            private String department;
            private int id;
            private String name;

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
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

        public static class LabelBean {
            public LabelBean(int id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public String toString() {
                return "LabelBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

            /**
             * id : 6
             * name : Digital transformation
             */

            private int id;
            private String name;

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

    public static class SponsorBean {
        /**
         * avatar: http://f1.kkmh.com/FlxJCJsoqqG7LwRc7N4l0Ki_JIaU-c.w320.jpg
         * country : null
         * created_at : 1.482215947986889E9
         * department : ITS/CD
         * email : lkiop@qq.com
         * id : 9
         * name : lkiop
         */
        private String avatar;
        private Object country;
        private double created_at;
        private String department;
        private String email;
        private int id;
        private String name;

        public SponsorBean(String avatar,Object country, double created_at, String department, String email, int id, String name) {
            this.avatar=avatar;
            this.country = country;
            this.created_at = created_at;
            this.department = department;
            this.email = email;
            this.id = id;
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
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
