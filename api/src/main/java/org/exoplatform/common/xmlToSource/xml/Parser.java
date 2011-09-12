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
package org.exoplatform.common.xmlToSource.xml;

import org.staxnav.Naming;
import org.staxnav.StaxNavException;
import org.staxnav.StaxNavigator;
import org.staxnav.StaxNavigatorImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/12/11
 */
public class Parser
{

   public static Map<String, MappingElement> parseDocument(InputStream in)
   {
      try
      {
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLStreamReader reader = factory.createXMLStreamReader(in);
         StaxNavigator<XmlElement> navigator = new StaxNavigatorImpl<XmlElement>(new Naming.Enumerated.Simple<XmlElement>(XmlElement.class, XmlElement.NO_SUCH_ELEMENT), reader);
         return parseDocument(navigator);
      }
      catch(Exception ex)
      {
         throw new RuntimeException(ex);
      }
      finally
      {
         try
         {
            if(in != null)
            {
               in.close();
            }
         }
         catch(Exception ioEx)
         {
            ioEx.printStackTrace();
         }
      }

   }

   public static Map<String, MappingElement> parseDocument(StaxNavigator<XmlElement> navigator)
   {
      Map<String, MappingElement> map = new HashMap<String, MappingElement>();

      for(StaxNavigator<XmlElement> mappingElement : navigator.fork(XmlElement.MAPPING))
      {
         MappingElement newMapping = parseMappingElement(mappingElement);
         map.put(newMapping.getQualifedClassName(), newMapping);
      }
      return map;
   }

   private static MappingElement parseMappingElement(StaxNavigator<XmlElement> navigator)
   {
      String qualifedClassName = navigator.getAttribute("class");
      List<SetterElement> setterElements = new LinkedList<SetterElement>();
      while(navigator.next(XmlElement.SETTER))
      {
         String methodName = navigator.getAttribute("methodName");
         String param = navigator.getAttribute("param");
         setterElements.add(new SetterElement(methodName, param));
      }

      return new MappingElement(qualifedClassName, setterElements);
   }

}
