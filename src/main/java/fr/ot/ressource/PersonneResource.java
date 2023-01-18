package fr.ot.ressource;

import fr.ot.entities.AdresseEntity;
import fr.ot.entities.PersonneEntity;
import fr.ot.entities.VilleEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.AdresseRepository;
import fr.ot.repository.EcoleRepository;
import fr.ot.repository.PersonneRepository;
import fr.ot.repository.VilleRepository;
import fr.ot.tools.EcoleTools;
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

@Path("/personnes")
@Tag(name = "Personnes")
@Produces(MediaType.APPLICATION_JSON)
public class PersonneResource {
    @Inject
    PersonneRepository personneRepository;
    @Inject
    EcoleRepository ecoleRepository;
    @Inject
    AdresseRepository adresseRepository;
    @Inject
    VilleRepository villeRepository;

    @GET
    public Response getAll(@Context UriInfo uriInfo){
        List<PersonneEntity> personnes = personneRepository.listAll();
        for(PersonneEntity personne : personnes){
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            personne.addLink("all", uriBase);
            personne.addLink("self", uriBase + "/" + personne.getIdPersonne());
        }
        return Response.ok(personnes).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            PersonneEntity personne = personneRepository.findById(id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            personne.addLink("all", uriBase.replace("/" + personne.getIdPersonne(), ""));
            personne.addLink("self", uriBase);
            return Response.ok(personne).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PersonneEntity personne, @Context UriInfo uriInfo){
        if(personne != null){
            System.out.println("1");
            AdresseEntity adresse = adresseRepository.findById(personne.getIdAdresse());
            System.out.println("2");
            VilleEntity ville = villeRepository.findById(adresse.getIdVille());
            System.out.println("3");
            int idClosestEcole = EcoleTools.getClosestFromCoordinates(ecoleRepository.listAll(), ville.getLatitude(), ville.getLongitude()).getIdEcole();
            System.out.println("4");
            System.out.println("idclosestecole");
            System.out.println(idClosestEcole);
            personne.setIdPersonne(idClosestEcole);

            personneRepository.persist(personne);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + personne.getIdPersonne());
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(PersonneEntity personne, @Context UriInfo uriInfo){
        if(personne != null){
            personneRepository.update("Nom = ?1, Prenom = ?2, VehiculeCV = ?3, id_adresse = ?4, id_ecole = ?5 where id_personne=?6", personne.getNom(), personne.getPrenom(), personne.getVehiculeCv(), personne.getIdAdresse(), personne.getIdEcole(),personne.getIdAdresse());
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + personne.getIdPersonne());
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            personneRepository.delete("id_personne = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            hateOas.addLink("self", uriBase);
            return Response.ok(hateOas).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
