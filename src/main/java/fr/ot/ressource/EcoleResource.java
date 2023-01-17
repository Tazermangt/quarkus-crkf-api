package fr.ot.ressource;

import fr.ot.entities.ClassificationEntity;
import fr.ot.entities.EcoleEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.ClassificationRepository;
import fr.ot.repository.EcoleRepository;
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

@Path("/ecoles")
@Tag(name = "Ecoles")
@Produces(MediaType.APPLICATION_JSON)
public class EcoleResource {

    @Inject
    EcoleRepository ecoleRepository;

    @GET
    public Response getAllEcoles(@Context UriInfo uriInfo) {
        List<EcoleEntity> ecoles = ecoleRepository.listAll();
        if (!ecoles.isEmpty()) {
            for (EcoleEntity ecoleEntity : ecoles) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                ecoleEntity.addLink("all", uriBase);
                ecoleEntity.addLink("self", uriBase + "/" + ecoleEntity.getIdEcole());
            }
            return Response.ok(ecoles).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EcoleEntity ecole = ecoleRepository.findById(id);
        if (ecole != null) {
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            ecole.addLink("all", uriBase.replace("/" + id, ""));
            ecole.addLink("self", uriBase);
            return Response.ok(ecole).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(EcoleEntity ecole, @Context UriInfo uriInfo) {
        if (ecole != null) {
            ecoleRepository.persist(ecole);
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + ecole.getIdEcole());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(EcoleEntity ecole, @Context UriInfo uriInfo) {
        if (ecole != null) {
            ecoleRepository.update("nom = ?1, id_adresse = ?2 where id_ecole = ?3", ecole.getNom(), ecole.getIdAdresse(), ecole.getIdEcole());
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + ecole.getIdEcole());
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
            ecoleRepository.delete("id_ecole = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
