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
package org.exoplatform.common.xmlToSource.source;

import org.exoplatform.common.xmlToSource.xml.MappingElement;
import org.exoplatform.common.xmlToSource.xml.Parser;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/12/11
 */
public class SourceGenerator
{

   public static Map<String, String> buildSource(InputStream is)
   {
      Map<String, MappingElement> mappingElements = Parser.parseDocument(is);
      return buildSource(mappingElements);
   }

   public static Map<String, String> buildSource(Map<String, MappingElement> mappingElements)
   {
      Map<String, String> codeSources = new HashMap<String, String>();
      for(String clazzName : mappingElements.keySet())
      {
         SourceBuilder builder = new SourceBuilder();
         builder.buildMappingElement(mappingElements.get(clazzName));
         codeSources.put(clazzName, builder.buildSource());
      }
      return codeSources;
   }
}
