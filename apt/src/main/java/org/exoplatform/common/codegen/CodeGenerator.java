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

import org.exoplatform.common.xmlToSource.source.SourceGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/8/11
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("*")
public class CodeGenerator extends AbstractProcessor
{

   private boolean processed = false;

   @Override
   public void init(ProcessingEnvironment processingEnv)
   {
      super.init(processingEnv);
   }

   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
   {
      if(!processed)
      {
         processed = true;
         _process(annotations, roundEnv, this.processingEnv);
      }
      return true;
   }

   public void _process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv, ProcessingEnvironment processingEnv)
   {
      Filer filer = processingEnv.getFiler();
      InputStream is = null;
      try
      {
         FileObject bindingFile = filer.getResource(StandardLocation.CLASS_PATH, "", "binding.xml");
         is = bindingFile.openInputStream();
      }
      catch (IOException ioEx)
      {
      }

      if(is == null)
      {
         return;
      }
      else
      {
         Map<String, String> codeSources = SourceGenerator.buildSource(is);


         for(String qualifiedClassName : codeSources.keySet())
         {
            int indexOfLastPoint = qualifiedClassName.lastIndexOf(".");
            String packageName = qualifiedClassName.substring(0, indexOfLastPoint);
            String typeName = qualifiedClassName.substring(indexOfLastPoint + 1, qualifiedClassName.length());

            Writer writer = null;
            try
            {
               JavaFileObject newCodeFile = filer.createSourceFile(packageName + ".Unmarshaller_" + typeName);
               System.out.println(codeSources.get(qualifiedClassName));
               writer = newCodeFile.openWriter();
               writer.write(codeSources.get(qualifiedClassName));
               writer.flush();
            }
            catch (IOException ioEx)
            {
               ioEx.printStackTrace();

            }
            finally
            {
               if (writer != null)
               {
                  try
                  {
                     writer.close();
                  }
                  catch (IOException ioEx)
                  {
                  }
               }
            }
         }
      }
   }
}
