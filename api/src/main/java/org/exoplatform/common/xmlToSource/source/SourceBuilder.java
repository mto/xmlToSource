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
import org.exoplatform.common.xmlToSource.xml.SetterElement;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/12/11
 */
public class SourceBuilder
{

   private final StringBuilder buf;

   public SourceBuilder()
   {
      buf = new StringBuilder();
   }

   public SourceBuilder buildMappingElement(MappingElement mappingElement)
   {
      if(buf.length() > 0)
      {
         throw new IllegalStateException();
      }

      String qualifiedName = mappingElement.getQualifedClassName();
      int lastPointIndex = qualifiedName.lastIndexOf(".");
      String packageName = qualifiedName.substring(0, lastPointIndex);
      String typeName = qualifiedName.substring(lastPointIndex + 1, qualifiedName.length());


      buf.append("package ").append(packageName).append(";\n");

      buf.append("\n");

      buf.append("import org.exoplatform.common.xmlToSource.api.model.Unmarshaller;").append("\n");
      buf.append("import static org.exoplatform.common.xmlToSource.api.model.UnmarshallerFactory.*;").append("\n");
      buf.append("import ").append(qualifiedName).append(";").append("\n");
      buf.append("\n");

      buf.append("public class Unmarshaller_").append(typeName);
      buf.append(" implements Unmarshaller<").append(typeName).append(">").append("\n");
      buf.append("{\n");

      buf.append("public static final Unmarshaller_").append(typeName).append(" INSTANCE;").append("\n");

      buf.append("static").append("\n");
      buf.append("{").append("\n");
      buf.append("INSTANCE = new Unmarshaller_").append(typeName).append("();").append("\n");
      buf.append("UNMARSHALLERs.put(\"").append(qualifiedName).append("\", INSTANCE);").append("\n");
      buf.append("}").append("\n");


      buf.append("@Override").append("\n");
      buf.append("public ").append(typeName).append(" unmarshall()").append("\n");
      buf.append("{").append("\n");

      buf.append(typeName).append(" re = ").append(" new ").append(typeName).append("()").append(";").append("\n");
      for(SetterElement setter : mappingElement.getSetterElements())
      {
         buildSetterElement(setter);
      }

      buf.append("\n");
      buf.append("return re;").append("\n");

      buf.append("}").append("\n");
      buf.append("}").append("\n");

      return this;
   }

   private SourceBuilder buildSetterElement(SetterElement setterElement)
   {
      buf.append("re.").append(setterElement.getMethodName()).append("(\"").append(setterElement.getParam()).append("\")").append(";").append("\n");
      return this;
   }


   public String buildSource()
   {
      return buf.toString();
   }
}
