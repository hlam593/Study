/**
 * TODO:
 *
 * @author sunyunhao
 * @date 2022/11/22 17:22
 */
public class CustomEntity{

        //主键id
        private Integer id;
        private String ptype;
        //平台
        private String platform;
        //企业id
        private String enterpriseId;
        //角色id
        private String roleId;
        //资源
        private String resource;
        //动作
        private String action;


    CustomEntity(String ptype, String platform, String enterpriseId, String roleId, String resource, String action){
            this.ptype = ptype;
            this.platform = platform;
            this.action = action;
            this.enterpriseId = enterpriseId;
            this.roleId = roleId;
            this.resource = resource;
    }



    public Integer getId() {
            return this.id;
    }

    public String getPtype() {
        return this.ptype;
    }
    public String getPlatform() {
        return this.platform;
    }
    public String getEnterpriseId() {
        return this.enterpriseId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getResource() {
        return this.resource;
    }

    public String getAction() {
        return this.action;
    }

    public static CustomEntityBuilder builder(){
        return new CustomEntityBuilder();
    }

    public static class CustomEntityBuilder{

        //ptype
        private String ptype;
        //平台
        private String platform;
        //企业id
        private String enterpriseId;
        //角色id
        private String roleId;
        //资源
        private String resource;
        //动作
        private String action;

        public CustomEntityBuilder ptype( String ptype){
            this.ptype = ptype;
            return this;
        }

        public CustomEntityBuilder platform( String platform){
            this.platform = platform;
            return this;
        }


        public CustomEntityBuilder enterpriseId( String enterpriseId){
            this.enterpriseId = enterpriseId;
            return this;
        }

        public CustomEntityBuilder roleId( String roleId){
            this.roleId = roleId;
            return this;
        }

        public CustomEntityBuilder resource( String resource){
            this.resource = resource;
            return this;
        }

        public CustomEntityBuilder action( String action){
            this.action = action;
            return this;
        }


       private CustomEntityBuilder(){}


        public CustomEntity build(){
            return new CustomEntity(this.ptype,this.platform,this.enterpriseId,this.roleId,this.resource,this.action);
        }
    }
}
