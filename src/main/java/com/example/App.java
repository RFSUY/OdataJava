package com.example;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import com.example.Controller.OdataController;

public class App 
{
     static String financialDimesion = "CostCenter";
     static String entity = "FinancialDimensionValues";

     static String entitySetName = "VendorInvoiceHeaders";
     static String filter = "dataAreaId eq 'bar'";
     static String orderBy = "HeaderReference desc";

     static String PurchaseOrder = "OC000001";
    public static void main( String[] args ) throws IOException, URISyntaxException, EdmPrimitiveTypeException
    {
          
          OdataController OdataController = new OdataController();
          
          //OdataController.getLastIdVendorInvoiceHeader(entitySetName, filter, orderBy);
          //OdataController.getFinancialDimensionValues(entity, financialDimesion);
          //OdataController.getTotalInvoiceToExpand(PurchaseOrder);
          OdataController.getTotalInvoiceToService();
          //OdataController.CreateFinancialDimension();
     }
}