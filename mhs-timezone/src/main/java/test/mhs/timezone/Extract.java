/**
 * Copyright 2008-2016 stefv
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.mhs.timezone;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author stefv
 */
public class Extract {

    public static void main(String[] args) throws IOException {
        final File file = new File(Extract.class.getClassLoader().getResource("./tz.json").getFile());
        final List<String> lines = Files.readAllLines(file.toPath());

        final StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line + "\n");
        }

        // System.out.println(sb.toString());

        final JSONObject obj = new JSONObject(sb.toString());
        final JSONArray data = obj.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            final JSONArray timezones = data.getJSONObject(i).getJSONArray("TimeZones");
            final List<String> tzLinks = getTimeZones(timezones);
            for (int tz = 0; tz < timezones.length(); tz++) {
                final String timezone = timezones.getString(tz);
                System.out.println("INSERT INTO TIMEZONE (NAME, LINK) VALUES ('', '" + timezone + "');");
            }
        }
    }
    
    private static List<String> getTimeZones(JSONArray timezones)
    {
        final List<String> tzLinks = new ArrayList<String>();
        for (int tz = 0; tz < timezones.length(); tz++) {
            final String timezone = timezones.getString(tz);
            tzLinks.add(timezone);
        }
        return tzLinks;
    }
}
