package fr.ot.ressource;

import fr.ot.entities.CompteEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.CompteRepository;
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

@Path("/comptes")
@Tag(name = "Comptes")
@Produces(MediaType.APPLICATION_JSON)
public class CompteResource {

    @Inject
    CompteRepository compteRepository;

    @GET
    public Response getAllCompte(@Context UriInfo uriInfo) {
        List<CompteEntity> comptes = compteRepository.listAll();
        if (!comptes.isEmpty()) {
            for (CompteEntity compteEntity : comptes) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                compteEntity.addLink("all", uriBase);
                compteEntity.addLink("self", uriBase + "/" + compteEntity.getIdCompte());
            }
            return Response.ok(comptes).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        CompteEntity compte = compteRepository.findById(id);
        if (compte != null) {
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            compte.addLink("all", uriBase.replace("/" + id, ""));
            compte.addLink("self", uriBase);
            return Response.ok(compte).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CompteEntity compte, @Context UriInfo uriInfo) {
        if (compte != null) {
            compteRepository.persist(compte);
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + compte.getIdCompte());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(CompteEntity compte, @Context UriInfo uriInfo) {
        if (compte != null) {
            compteRepository.update("email = ?1, password = ?2 where id_compte = ?3", compte.getEmail(), compte.getPassword(), compte.getIdCompte());
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + compte.getIdCompte());
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
            compteRepository.delete("id_compte = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
