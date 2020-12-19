package kz.edu.nu.cs.exercise;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/items")
public class PagingService {

    private List<String> list = new CopyOnWriteArrayList<String>();
    public PagingService() {
        for (int i = 0; i < 100; i++) {
            list.add("Entry " + String.valueOf(i));
        }
    }

    @GET
    public Response getMyList(@QueryParam("pagenum") int pagenum) {

        Gson gson = new Gson();
        String json;
        
        PagedHelper p = new PagedHelper();

        p.setList(list.subList(10 * pagenum, 10 * (pagenum + 1)));


        p.setNext("next url (from Paging Service)");
        p.setPrev("prev url (from Paging Service)");
        
        json = gson.toJson(p, PagedHelper.class);
        
        return Response.ok(json).build();
    }
}
