package data;

import org.testng.annotations.DataProvider;
import tests.BaseTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataProviders extends BaseTest {


    @DataProvider(name = "paymentInitiationTypes")
    Iterator<Object[]> paymentInitiationTypes() throws InterruptedException, SQLException, ClassNotFoundException {
        Data.LoginUsers data = new Data.LoginUsers();
        String user = data.ROL_1_NURSE_NEONATOS.userName;


        ArrayList<Object> camarasList = new ArrayList<Object>();
        ResultSet resultSet= getDataBaseQueryResult("select idCamara from sanitaswebcamNew.Camara where \n" +
                "idHospital=(select idHospital from sanitaswebcamNew.Usuario_has_Hospital where \n" +
                "idUsuario=(select idUsuario from sanitaswebcamNew.Usuario where email = \""+user+"\"))");

        while (resultSet.next())
        {
            //camarasList.add(resultSet.getString(1));
        }

        Collection<Object[]> paymentTypesCollection = new ArrayList<Object[]>();

        List<Map<String, String>> listOfParamMaps = new ArrayList<Map<String, String>>();
        Map<String, String> domesticParameterMap = new HashMap<String, String>();
        Map<String, String> internationalParameterMap = new HashMap<String, String>();

        //domesticParameterMap.put("path", TestConstants.CONSENT_PATH_DOMESTIC);
        //domesticParameterMap.put("payload", TestConstants.DOMESTIC_PAYMENTS);

        //internationalParameterMap.put("path", TestConstants.CONSENT_PATH_INTERNATIONAL);
        //internationalParameterMap.put("payload", TestConstants.INTERNATIONAL_PAYMENTS);

        listOfParamMaps.add(domesticParameterMap);
        listOfParamMaps.add(internationalParameterMap);

        for (Map<String, String> map : listOfParamMaps) {
            paymentTypesCollection.add(Object[].class.cast(map));
        }
        return paymentTypesCollection.iterator();
    }
}