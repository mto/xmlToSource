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
package org.exoplatform.common.xmlToSource.parser;

import junit.framework.TestCase;
import java.lang.reflect.Method;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/18/11
 */
public class TestGeneratedCode extends TestCase
{

   @Override
   protected void setUp() throws Exception
   {
      super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
   }

   public void testExecuteGeneratedCode()
   {
      try
      {
         Class clazz = Class.forName("org.exoplatform.common.xmlToSource.parser.TestClassA");
         assertNotNull(clazz);
         assertEquals("org.exoplatform.common.xmlToSource.parser.TestClassA", clazz.getName());

         Method mainMethod = clazz.getMethod("main", new String[0].getClass());
         assertNotNull(mainMethod);
         mainMethod.invoke(null, new String[1]);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   @Override
   protected void tearDown() throws Exception
   {
      super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
   }
}
