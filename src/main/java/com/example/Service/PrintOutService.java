package com.example.Service;

import java.util.List;
import org.apache.olingo.client.api.domain.ClientCollectionValue;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.domain.ClientValue;
import com.example.Service.PrintOutService;

public class PrintOutService {
    
    public void FinancialDimensionValue(ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator) {
        while (iterator.hasNext()) {
            ClientEntity ce = iterator.next();
            System.out.println("FinancialDimension: " + ce.getProperty("FinancialDimension").getPrimitiveValue()
                    + " - value: " + ce.getProperty("DimensionValue").getPrimitiveValue());
        }
    }

    
    public void LastIdInvoice(ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator) {
        while (iterator.hasNext()) {
            ClientEntity ce = iterator.next();
            System.out.println("Invoice: " + ce.getProperty("InvoiceNumber").getPrimitiveValue()
                    + " - LastHeaderReference: " + ce.getProperty("HeaderReference").getPrimitiveValue());
        }
    }

    public void TotalInvoiceToExpand(ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator) {
        while (iterator.hasNext()) {
            ClientEntity entity = iterator.next();

            List<ClientProperty> properties = entity.getProperties();

            ClientValue purchaseOrderNumber = properties.get(0).getValue();

            System.out.println("PurchaseOrderNumber: " + purchaseOrderNumber);
            ClientValue entityline = properties.get(1).getValue();
            ClientCollectionValue<ClientValue> value = entityline.asCollection();
            double totalLineAmount = 0;
            for (ClientValue line : value) {

                ClientComplexValue firstPurchaseOrderLine = line.asComplex();
                int asdd = (int) firstPurchaseOrderLine.get("LineAmount").getPrimitiveValue().toValue();
                totalLineAmount += asdd;
            }

            System.out.println("LineAmountSum: " + totalLineAmount);

        }
    }
}
