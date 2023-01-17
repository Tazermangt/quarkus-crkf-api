package fr.ot;

import fr.ot.entities.FamilleEntity;
import fr.ot.repository.FamilleRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.UriInfo;

@Path("/familles")
@Tag(name = "Familles")
@Produces (MediaType.APPLICATION_JSON)
public class FamilleResource {

    @Inject
    FamilleRepository familleRepository;

    @GET
    public Response getAll(@Context UriInfo uriInfo){
        List<FamilleEntity> familles = familleRepository.listAll();
        for(FamilleEntity famille : familles){
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            famille.addLink("all", uriBase);
            famille.addLink("self", uriBase + "/" + famille.getIdFamille());
        }
        return Response.ok(familles).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            FamilleEntity famille = familleRepository.findById(id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            famille.addLink("all", uriBase);
            famille.addLink("self", uriBase + "/" + famille.getIdFamille());
            return Response.ok(famille).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(FamilleEntity famille, @Context UriInfo uriInfo){
        if(famille != null){
            System.out.println(famille);
            familleRepository.persist(famille);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(FamilleEntity famille){
        if(famille != null){
            familleRepository.update("Famille = ?1 id_classification = ?2 where id_famille=?3", famille.getFamille(), famille.getIdClassification(),famille.getIdFamille());
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        if(id != null){
            familleRepository.delete("id_famille = ?1", id);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
