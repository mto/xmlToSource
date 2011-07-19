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
package org.exoplatform.common.codegen;

import org.exoplatform.common.xmlToSource.annotation.TypeInfo;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/8/11
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes({"org.exoplatform.common.xmlToSource.annotation.TypeInfo"})
public class CodeGenerator extends AbstractProcessor
{


   @Override
   public void init(ProcessingEnvironment processingEnv)
   {
      super.init(processingEnv);
   }

   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
   {
      if(annotations.size() > 0)
      {
         _process(annotations, roundEnv, this.processingEnv);
      }
      return true;
   }

   public boolean _process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv, ProcessingEnvironment processingEnv)
   {
      Filer filer = processingEnv.getFiler();
      JavaFileObject fileObject;
      Writer writer = null;
      try
      {
         fileObject = filer.createSourceFile("org.exoplatform.common.xmlToSource.parser.TestClassA");
         writer = fileObject.openWriter();

         StringBuilder source = new StringBuilder();
         source.append("package org.exoplatform.common.xmlToSource.parser;\n");
         source.append("public class TestClassA\n{\n");

         source.append("public static void main(String[] args)\n{\n System.out.println(\"Hoang loves Trang\"); \n}\n");
         source.append("}");
         writer.append(source);
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         try{
            writer.flush();
            writer.close();
         }
         catch(IOException ex)
         {

         }
      }

      return true;
   }
}
