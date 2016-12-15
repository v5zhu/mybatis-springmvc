package impl.facade;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.v5zhu.dubbo.api.UserService;
import com.v5zhu.dubbo.api.facade.UserRestService;
import com.v5zhu.dubbo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zhuxl@paxsz.com on 2016/7/25.
 */
@Service(protocol = "rest")
@Path("/v1/")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_HTML})
@Produces(MediaType.APPLICATION_JSON)
public class UserRestServiceImpl implements UserRestService {
    private static Logger logger = LoggerFactory.getLogger(UserRestServiceImpl.class);

    @Autowired
    private UserService userService;

    @GET
    @Path("user")
    @Override
    public Response findByLoginName(@QueryParam("loginName") String loginName) {
        logger.info("请求参数:[{}]",loginName);
        UserDto userDto = userService.findByLoginName(loginName);
        logger.info("请求返回的数据:[{}]", JSONObject.toJSONString(userDto));
        return Response.status(Response.Status.OK).entity(userDto).build();
    }

    @POST
    @Path("user")
    @Override
    public Response addUser(UserDto userDto) {
        try {
            userService.createUser(userDto);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","新增用户成功");
            return Response.status(Response.Status.OK).entity(ok).build();
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Path("user/{userId}")
    @Override
    public Response modifyUser(@PathParam("userId") Long userId, UserDto userDto) {
        try {
            userService.modifyUser(userId,userDto);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","修改用户成功");
            return Response.status(Response.Status.OK).entity(ok).build();
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @DELETE
    @Path("user/{userId}")
    @Override
    public Response deleteUser(@PathParam("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","删除用户成功");
            return Response.status(Response.Status.OK).entity(ok).build();
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }
}
