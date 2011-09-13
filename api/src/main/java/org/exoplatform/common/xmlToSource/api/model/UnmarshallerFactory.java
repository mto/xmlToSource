/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.common.xmlToSource.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/12/11
 */
public class UnmarshallerFactory
{
   //Don't know how to make that work for the moment as static{} block is only executed after initializing phase
   public final static Map<String, Unmarshaller<?>> UNMARSHALLERs = new HashMap<String, Unmarshaller<?>>();

   public static <T> Unmarshaller<T> getUnmarshaller(Class<T> type)
   {
      Unmarshaller unmarshaller = UNMARSHALLERs.get(type.getCanonicalName());
      if(unmarshaller != null)
      {
         return (Unmarshaller<T>)unmarshaller;
      }
      else
      {
         return null;
      }
   }
}
