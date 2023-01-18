package fr.ot.ressource;

import fr.ot.entities.AdresseEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.AdresseRepository;
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

@Path("/adresses")
@Tag(name = "Adresses")
@Produces(MediaType.APPLICATION_JSON)
public class AdresseResource {
    @Inject
    AdresseRepository adresseRepository;

    @GET
    public Response getAll(@Context UriInfo uriInfo){
        List<AdresseEntity> adresses = adresseRepository.listAll();
        for(AdresseEntity adresse : adresses){
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            adresse.addLink("all", uriBase);
            adresse.addLink("self", uriBase + "/" + adresse.getIdAdresse());
        }
        return Response.ok(adresses).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            AdresseEntity adresse = adresseRepository.findById(id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            adresse.addLink("all", uriBase.replace("/" + adresse.getIdAdresse(), ""));
            adresse.addLink("self", uriBase);
            return Response.ok(adresse).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AdresseEntity adresse, @Context UriInfo uriInfo){
        if(adresse != null){
            adresseRepository.persist(adresse);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + adresse.getIdAdresse());
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(AdresseEntity adresse, @Context UriInfo uriInfo){
        if(adresse != null){
            adresseRepository.update("Adresse = ?1, id_ville = ?2 where id_adresse=?3", adresse.getAdresse(), adresse.getIdVille(), adresse.getIdAdresse());
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + adresse.getIdAdresse());
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            adresseRepository.delete("id_adresse = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            hateOas.addLink("self", uriBase);
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
