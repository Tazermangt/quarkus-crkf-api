package fr.ot.ressource;

import fr.ot.entities.FamilleEntity;
import fr.ot.entities.InstrumentEntity;
import fr.ot.repository.FamilleRepository;
import fr.ot.repository.InstrumentRepository;
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

@Path("/instruments")
@Tag(name = "Instruments")
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentRessource {
    @Inject
    InstrumentRepository instrumentRepository;

    @GET
    public Response getAll(@Context UriInfo uriInfo){
        List<InstrumentEntity> instruments = instrumentRepository.listAll();
        for(InstrumentEntity instrument : instruments){
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            instrument.addLink("all", uriBase);
            instrument.addLink("self", uriBase + "/" + instrument.getIdInstrument());
        }
        return Response.ok(instruments).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo){
        if(id != null){
            InstrumentEntity instrument = instrumentRepository.findById(id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            instrument.addLink("all", uriBase);
            instrument.addLink("self", uriBase + "/" + instrument.getIdInstrument());
            return Response.ok(instrument).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(InstrumentEntity instrument, @Context UriInfo uriInfo){
        if(instrument != null){
            instrumentRepository.persist(instrument);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(InstrumentEntity instrument){
        if(instrument != null){
            instrumentRepository.update("Nom = ?1 where id_instrument=?2", instrument.getNom(), instrument.getIdInstrument());
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        if(id != null){
            instrumentRepository.delete("id_instrument = ?1", id);
            return Response.status(204).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
