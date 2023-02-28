package com.example.Model;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class PurchaseOrder {
    private String OC;

    public String getoc() {
        return OC;
    }

    public void setoc(String oc) {
        OC = oc;
    }

    public ClientEntity NumberPurchaseOrderToClientEntity(ODataClient odataClient) {

        ClientEntity entityToCreate = odataClient.getObjectFactory()
                .newEntity(new FullQualifiedName("OC.OC"));

        List<ClientProperty> properties = new ArrayList<ClientProperty>();

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("_oc",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString("OC000001")));

        entityToCreate.getProperties().addAll(properties);

        return entityToCreate;
    }
}
