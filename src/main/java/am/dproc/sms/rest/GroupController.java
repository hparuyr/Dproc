package am.dproc.sms.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Group;
import am.dproc.sms.services.interfaces.GroupService;
import io.swagger.annotations.Api;

@RestController
@Path("/groupService")
@Api(value = "GroupController")
public class GroupController {

    @Autowired
    GroupService groupService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response addGroup(Group group) {
        Integer id = groupService.addGroup(group);
        if (id == -1) {
            Map<String, String> message = new HashMap<String, String>();
            message.put("Message", "All fields must be filled!");
            return Response.status(Status.BAD_REQUEST).entity(message).build();
        } else if (id == 0) {
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.status(Status.CREATED).entity(id).build();
    }

    @Path("/all")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGroups(List<Group> groups) {
        List<Integer> ids = this.groupService.addGroups(groups);
        if (ids.size() > 0) {
            return Response.status(Status.CREATED).entity(ids).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/{id}")
    public Group getGroup(@PathParam(value = "id") Integer id) {
        return groupService.getGroup(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateGroup(Group group) {
        if (this.groupService.updateGroup(group) == 1) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/{id}")
    public Response deleteUser(@PathParam(value = "id") Integer id) {
        if (groupService.deleteGroup(id) == 1) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

}
