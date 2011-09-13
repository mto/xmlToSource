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
package org.exoplatform.common.xmlToSource.sample;

import junit.framework.TestCase;
import org.exoplatform.common.xmlToSource.api.model.Unmarshaller;
import org.exoplatform.common.xmlToSource.api.model.UnmarshallerFactory;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/12/11
 */
public class TestBindingObject extends TestCase
{

   public void testBindingObject() throws Exception
   {
      //Still use java.lang.reflect, but merely to load the unmarshaller into JVM
      Class<Unmarshaller<Foo>> fooUnmarshallerClazz = (Class<Unmarshaller<Foo>>)Class.forName("org.exoplatform.common.xmlToSource.sample.Unmarshaller_Foo");
      Unmarshaller<Foo> fooUnmarshaller = fooUnmarshallerClazz.newInstance();
      Foo foo = fooUnmarshaller.unmarshall();

      assertNotNull(foo);
      assertEquals("toto", foo.firstField);
      assertEquals("titi", foo.secondField);
      assertEquals("tutu", foo.thirdField);

      Class<Unmarshaller<Bar>> barUnmarshallerClazz = (Class<Unmarshaller<Bar>>)Class.forName("org.exoplatform.common.xmlToSource.sample.Unmarshaller_Bar");
      Unmarshaller<Bar> barUnmarshaller = barUnmarshallerClazz.newInstance();
      Bar bar = barUnmarshaller.unmarshall();

      assertNotNull(bar);
      assertEquals("XYZ", bar.barField);
   }
}
