/*
 * Copyright (C) 2012 jfrankie (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package adapter;

public class CountriesList {

	private int  countryid;
    private String countryname;
    private String countryip;







	public CountriesList(int countryid, String countryname, String countryip) {
		this.countryid = countryid;
this.countryname = countryname;
        this.countryip = countryip;

	}



	public int getcountryid() {
		return countryid;
	}


	public String getcountryname() {
		return countryname;
	}


    public String getcountryip() {
        return countryip;
    }





	
	
}
