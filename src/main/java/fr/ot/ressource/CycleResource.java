package fr.ot.ressource;

import fr.ot.entities.CompteEntity;
import fr.ot.entities.CycleEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.CompteRepository;
import fr.ot.repository.CycleRepository;
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

@Path("/cycles")
@Tag(name = "Cycles")
@Produces(MediaType.APPLICATION_JSON)
public class CycleResource {

    @Inject
    CycleRepository cycleRepository;

    @GET
    public Response getAllCycles(@Context UriInfo uriInfo) {
        List<CycleEntity> cycles = cycleRepository.listAll();
        if (!cycles.isEmpty()) {
            for (CycleEntity cycleEntity : cycles) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                cycleEntity.addLink("all", uriBase);
                cycleEntity.addLink("self", uriBase + "/" + cycleEntity.getIdLibelle());
            }
            return Response.ok(cycles).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        CycleEntity cycle = cycleRepository.findById(id);
        if (cycle != null) {
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            cycle.addLink("all", uriBase.replace("/" + id, ""));
            cycle.addLink("self", uriBase);
            return Response.ok(cycle).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CycleEntity cycle, @Context UriInfo uriInfo) {
        if (cycle != null) {
            cycleRepository.persist(cycle);
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + cycle.getIdLibelle());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(CycleEntity cycle, @Context UriInfo uriInfo) {
        if (cycle != null) {
            cycleRepository.update("libelle = ?1, cycle = ?2 where id_libelle = ?3", cycle.getLibelle(), cycle.getCycle(), cycle.getIdLibelle());
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + cycle.getIdLibelle());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        if (id != null) {
            cycleRepository.delete("id_libelle = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
