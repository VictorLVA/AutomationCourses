package HttpClientsImplementation;

import Constants.HttpMethods;
import HttpClientsImplementation.Utils.CustomResponse;
import HttpClientsImplementation.Utils.RequestData;

public interface CustomHttpClient {

    CustomResponse doRequest(HttpMethods httpMethods, RequestData requestData);
}