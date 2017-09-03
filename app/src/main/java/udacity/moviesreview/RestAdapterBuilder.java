package udacity.moviesreview;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestAdapterBuilder
{
    public RestAdapter restAdapter(String mainURL, final String key, final String valuekey)
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(mainURL).setRequestInterceptor(new RequestInterceptor()
        {
            @Override
            public void intercept(RequestFacade request)
            {
                request.addEncodedQueryParam(key, valuekey);
            }
        }).setLogLevel(RestAdapter.LogLevel.FULL).build();

        return restAdapter;
    }
}
