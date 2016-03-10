package com.iancaffey.steam;

import com.iancaffey.steam.util.Strings;
import com.iancaffey.steam.util.Time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Communicator
 * <p>
 * An object which bridges to the Steam WebAPI and retrieves the response for certain WebAPI methods.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Communicator implements AutoCloseable {
    private static final String FORMAT_PATTERN = "&format=%s";
    private static final String QUERY_FORMAT = "http://api.steampowered.com/%s/%s/%s/?key=%s&%s" + FORMAT_PATTERN;
    private final Steam steam;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Creates a new communicator for the Steam API wrapper.
     *
     * @param steam the steam API reference
     * @throws IllegalArgumentException if <code>steam == null</code>
     */
    public Communicator(Steam steam) {
        this.steam = steam;
    }

    /**
     * Terminates all current communications with the Steam WebAPI.
     */
    @Override
    public void close() {
        executor.shutdown();
    }

    /**
     * Retrieves the response test from the Steam WebAPI for the specified method in JSON.
     *
     * @param method     the method to call with the Steam WebAPI
     * @param parameters the parameters to add to the query string
     * @return <code>null</code> if unable to communicate with the Steam WebAPI, otherwise the valid response text
     */
    public String retrieve(Method method, Object... parameters) {
        return retrieve(method, DataFormat.JSON, parameters);
    }

    /**
     * Retrieves the response test from the Steam WebAPI for the specified method in the target format.
     *
     * @param method     the method to call with the Steam WebAPI
     * @param format     the format in which to retrieve the data in
     * @param parameters the parameters to add to the query string
     * @return <code>null</code> if unable to communicate with the Steam WebAPI, otherwise the valid response text
     */
    public String retrieve(Method method, DataFormat format, Object... parameters) {
        return retrieve(generateURL(method, format, parameters), method.getRequestMethod());
    }

    /**
     * Retrieves the response test from the Steam API at the specified URL using a specific data format.
     *
     * @param loc           the location of the Steam API resource
     * @param format        the format in which to retrieve the data in
     * @param requestMethod the type of request method for the HTTP request (POST, GET, PUT, DELETE)
     * @return <code>null</code> if unable to communicate with the Steam WebAPI, otherwise the valid response text
     */
    public String retrieve(String loc, DataFormat format, RequestMethod requestMethod) {
        if (loc == null)
            return null;
        if (format == null)
            format = DataFormat.JSON;
        String token = String.format(FORMAT_PATTERN, format.getToken());
        return retrieve(loc.contains(token) ? loc : loc + token, requestMethod);
    }

    /**
     * Retrieves the response test from the Steam API at the specified URL.
     *
     * @param loc           the location of the Steam API resource
     * @param requestMethod the type of request method for the HTTP request (POST, GET, PUT, DELETE)
     * @return <code>null</code> if unable to communicate with the Steam WebAPI, otherwise the valid response text
     */
    public String retrieve(final String loc, final RequestMethod requestMethod) {
        if (loc == null)
            return null;
        Future<String> retriever = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(loc).openConnection();
                    if (requestMethod != null)
                        connection.setRequestMethod(requestMethod.name());
                    if (connection.getResponseCode() != 200)
                        return null;
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder builder = new StringBuilder();
                        String string;
                        while ((string = reader.readLine()) != null)
                            builder.append(string);
                        return builder.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        while (!retriever.isDone())
            Time.sleep(50);
        try {
            return retriever.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a Steam WebAPI query string for the method using English.
     *
     * @param method     the method to be called with the Steam WebAPI
     * @param parameters the parameters to add to the query string
     * @return <code>null</code> if <code>method == null</code> or <code>format == null</code>, a valid Steam WebAPI query string otherwise.
     */

    public String generateURL(Method method, Object... parameters) {
        return generateURL(method, DataFormat.JSON, parameters);
    }

    /**
     * Creates a Steam WebAPI query string for the method using the proper data format.
     *
     * @param method     the method to be called with the Steam WebAPI
     * @param format     the format in which to return data
     * @param parameters the parameters to add to the query string
     * @return <code>null</code> if <code>method == null</code>, a valid Steam WebAPI query string otherwise.
     */
    public String generateURL(Method method, DataFormat format, Object... parameters) {
        if (method == null)
            return null;
        String key = steam.getAPIKey();
        if (key == null)
            return null;
        if (format == null)
            format = DataFormat.JSON;
        String parameterFormat = method.getParameterFormat();
        if (parameterFormat == null && parameters != null)
            return null;
        String[] parsedParameters = parameters == null ? null : new String[parameters.length];
        if (parsedParameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (!parameters[i].getClass().isArray()) {
                    if (parameters[i] instanceof Boolean)
                        parameters[i] = (Boolean) parameters[i] ? 1 : 0;
                    parsedParameters[i] = String.valueOf(parameters[i]);
                    continue;
                } else {
                    String string = null;
                    if (parameters[i] instanceof byte[]) {
                        string = Arrays.toString((byte[]) parameters[i]);
                    } else if (parameters[i] instanceof int[]) {
                        string = Arrays.toString((int[]) parameters[i]);
                    } else if (parameters[i] instanceof long[]) {
                        string = Arrays.toString((long[]) parameters[i]);
                    } else if (parameters[i] instanceof short[]) {
                        string = Arrays.toString((short[]) parameters[i]);
                    } else if (parameters[i] instanceof boolean[]) {
                        string = Arrays.toString((boolean[]) parameters[i]).replaceAll("true", "1").replaceAll("false", "0");
                    } else if (parameters[i] instanceof double[]) {
                        string = Arrays.toString((double[]) parameters[i]);
                    }
                    if (string != null) {
                        if (string.length() > 2)
                            string = string.substring(1, string.length() - 1);
                        parsedParameters[i] = string.replaceAll(" ", "");
                        continue;
                    }
                }
                int parameterFormatTokenIndex = Strings.nthIndexOf(parameterFormat, "%s", i);
                if (parameterFormatTokenIndex == -1)
                    throw new IllegalStateException("Unable to parse array into named segments");
                int proceedingIndex = Strings.nthIndexOf(parameterFormat, "%s", i - 1);
                if (proceedingIndex != -1)
                    proceedingIndex += 3;
                else
                    proceedingIndex = 0;
                String arrayName = parameterFormat.substring(proceedingIndex, parameterFormatTokenIndex - 1);
                parameterFormat = parameterFormat.substring(0, proceedingIndex) + parameterFormat.substring(parameterFormatTokenIndex);
                int length = ((Object[]) parameters[i]).length;
                String individualFormat = "";
                for (int f = 0; f < length; f++)
                    individualFormat += String.format("%s[%s]=%s" + (f != length - 1 ? "&" : ""), arrayName, f, "%s");
                parsedParameters[i] = String.format(individualFormat, ((Object[]) parameters[i]));
            }
        }
        return String.format(QUERY_FORMAT,
                method.getInterface().getToken(),
                method.getToken(),
                method.getVersion().getToken(),
                key,
                parsedParameters == null || parsedParameters.length == 0 ? "" :
                        String.format(parameterFormat, (Object[]) parsedParameters),
                format.getToken());
    }
}
