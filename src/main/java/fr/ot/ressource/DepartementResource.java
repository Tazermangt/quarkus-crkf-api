package fr.ot.ressource;

import fr.ot.entities.DepartementEntity;
import fr.ot.entities.InstrumentEntity;
import fr.ot.repository.DepartementRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/departements")
@Tag(name = "Departements")
@Produces(MediaType.APPLICATION_JSON)
public class DepartementResource {
    @Inject
    DepartementRepository departementRepository;

    @GET
    public Response getAll(@Context UriInfo uriInfo){
        List<DepartementEntity> departements = departementRepository.listAll();
        for(DepartementEntity departement : departements){
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            departement.addLink("all", uriBase);
            departement.addLink("self", uriBase + "/" + departement.getIdDepartement());
        }
        return Response.ok(departements).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            DepartementEntity departement = departementRepository.findById(id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            departement.addLink("all", uriBase);
            departement.addLink("self", uriBase + "/" + departement.getIdDepartement());
            return Response.ok(departement).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(DepartementEntity departement, @Context UriInfo uriInfo){
        if(departement != null){
            departementRepository.persist(departement);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(DepartementEntity departement){
        if(departement != null){
            departementRepository.update("Departement = ?1, numero_departement =?2 where id_departement = ?3", departement.getDepartement(), departement.getNumeroDepartement(), departement.getIdDepartement() );
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        if(id != null){
            departementRepository.delete("id_departement = ?1", id);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
