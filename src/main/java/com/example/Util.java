package com.example;

import java.net.URI;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.uri.URIBuilder;
import org.apache.olingo.client.core.ODataClientFactory;

public class Util {

    final String serviceUrlData = "https://usnconeboxax1aos.cloud.onebox.dynamics.com/data/";
    final String serviceUrlAPI = "https://usnconeboxax1aos.cloud.onebox.dynamics.com/api/";
    final String serviceUrlToken = "https://login.microsoftonline.com/rfsuy.com/oauth2/v2.0/";

    public ODataClient getClient() {

        org.apache.olingo.client.api.ODataClient client = ODataClientFactory.getClient();

        return client;
    }

    public URI getUriToPath(ODataClient client, String financialDimension) {

        String entitySetName = "FinancialDimensionValues?cross-company=true&$filter=FinancialDimension%20eq%20%27" + financialDimension + "%27";
        URI uri = client.newURIBuilder(serviceUrlData).appendEntitySetSegment(entitySetName).build();
        return uri;
    }

    public URI getUriToMethod(ODataClient client, String entity, String financialDimension) {
        
        URIBuilder OdataUriBuilder = client.newURIBuilder(serviceUrlData).appendEntitySetSegment(entity);
        OdataUriBuilder.filter("FinancialDimension eq '" + financialDimension + "'");
        URI uri = OdataUriBuilder.build();

        return uri;
    }

    public URI getUriLastRegister(ODataClient client, String entity, String filter, String orderBy) {     

        URIBuilder OdataUriBuilder = client.newURIBuilder(serviceUrlData).appendEntitySetSegment(entity);
        OdataUriBuilder.filter(filter);
        OdataUriBuilder.orderBy(orderBy);
        OdataUriBuilder.top(1);
        URI uri = OdataUriBuilder.build();

        return uri;
    }

    public URI getAbsolutUriToken(ODataClient client) {

        String entitySetName = "token";
        URI absoluteUri = client.newURIBuilder(serviceUrlToken).appendEntitySetSegment(entitySetName).build();
        return absoluteUri;
    }

    public URI getUriExpand(ODataClient client, String purchaseOrder) {

        URIBuilder uriBuilder = client
                .newURIBuilder(serviceUrlData + "PurchaseOrderHeadersV2");
        uriBuilder.select("PurchaseOrderNumber");
        uriBuilder.filter("PurchaseOrderNumber eq '" + purchaseOrder + "'");
        uriBuilder.expand("PurchaseOrderLinesV2");
        URI uri = uriBuilder.build();

        return uri;
    }

    public URI getAbsolutUri(ODataClient client) {

        String entitySetName = "FinancialDimensionValues";
        URI absoluteUri = client.newURIBuilder(serviceUrlData).appendEntitySetSegment(entitySetName).build();
        return absoluteUri;
    }

    public URI getAbsolutUriService(ODataClient client) {
      
        String entitySetName = "services/TestOdataGetPOTotalServiceGroup/GetTotalPOService/GetTotalPO";
        URI absoluteUri = client.newURIBuilder(serviceUrlAPI).appendEntitySetSegment(entitySetName).build();
        return absoluteUri;
    }
}
