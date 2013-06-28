/*
 * Copyright 2010-2013 Geraldo Ferraz de Oliveira Filho
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */
package calculadora;

public class Tempo {

	private final long dias;
	private final long horas;
	private final long minutos;

	public Tempo(long dias, long horas, long minutos) {
		this.dias = dias;
		this.horas = horas;
		this.minutos = minutos;
	}

	public long getDias() {
		return dias;
	}

	public long getHoras() {
		return horas;
	}

	public long getMinutos() {
		return minutos;
	}

	@Override
	public String toString() {
		
		return dias+","+horas+":"+minutos;
	}
}
