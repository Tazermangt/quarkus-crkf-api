package fr.ot.ressource;

import fr.ot.entities.ClassificationEntity;
import fr.ot.entities.VilleEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.ClassificationRepository;
import fr.ot.repository.VilleRepository;
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

@Path("/villes")
@Tag(name = "Villes")
@Produces(MediaType.APPLICATION_JSON)
public class VilleResource {

    @Inject
    VilleRepository villeRepository;

    @GET
    public Response getAllVilles(@Context UriInfo uriInfo) {
        List<VilleEntity> villes = villeRepository.listAll();
        if (!villes.isEmpty()) {
            for (VilleEntity villeEntity : villes) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                villeEntity.addLink("all", uriBase);
                villeEntity.addLink("self", uriBase + "/" + villeEntity.getIdVille());
            }
            return Response.ok(villes).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        VilleEntity ville = villeRepository.findById(id);
        if (ville != null) {
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            ville.addLink("all", uriBase.replace("/" + id, ""));
            ville.addLink("self", uriBase);
            return Response.ok(ville).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(VilleEntity ville, @Context UriInfo uriInfo) {
        if (ville != null) {
            villeRepository.persist(ville);
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase.replace("/" + ville.getIdVille(), ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(VilleEntity ville, @Context UriInfo uriInfo) {
        if (ville != null) {
            villeRepository.update("ville = ?1 where id_ville=?2", ville.getVille(), ville.getIdVille());
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + ville.getIdVille());
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
            villeRepository.delete("id_ville = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
