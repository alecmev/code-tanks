package com.a.b.a.a;

import com.a.b.b.a;
import com.a.b.b.d;
import javax.vecmath.Vector2d;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Shape;

class c
{
  private c()
  {
    throw new UnsupportedOperationException();
  }

  public static Body a(com.a.b.b paramb)
  {
    if (paramb.a() != null)
      throw new IllegalArgumentException("Can't create new body with specific ID.");
    paramb = new com.a.b.b(paramb);
    Object localObject;
    if (paramb.p())
    {
      localObject = new StaticBody(paramb.b(), a(paramb.o()));
      paramb.i(((Body)localObject).getMass());
    }
    else
    {
      localObject = new Body(paramb.b(), a(paramb.o()), (float)paramb.n());
    }
    paramb.a(Long.valueOf(((Body)localObject).getID()));
    a((Body)localObject, paramb);
    return localObject;
  }

  public static com.a.b.b a(Body paramBody)
  {
    return new com.a.b.b(Long.valueOf(paramBody.getID()), paramBody.getName(), paramBody.getPosition().getX(), paramBody.getPosition().getY(), paramBody.getRotation(), new Vector2d(paramBody.getVelocity().getX(), paramBody.getVelocity().getY()), paramBody.getAngularVelocity(), new Vector2d(paramBody.getForce().getX(), paramBody.getForce().getY()), paramBody.getTorque(), paramBody.getDamping(), paramBody.getRotDamping(), paramBody.getRestitution(), paramBody.getFriction(), paramBody.getMass(), a(paramBody.getShape()), paramBody.isStatic());
  }

  public static void a(Body paramBody, com.a.b.b paramb)
  {
    if ((paramb.a() == null) || (paramb.a().longValue() != paramBody.getID()))
      throw new IllegalArgumentException("Can't update body ID.");
    boolean bool = paramb.b() == null ? false : paramBody.getName() == null ? true : paramb.b().equals(paramBody.getName());
    if (!bool)
      throw new IllegalArgumentException("Can't update body name.");
    if ((StrictMath.abs(paramb.c() - paramBody.getPosition().getX()) > 1.0E-006D) || (StrictMath.abs(paramb.d() - paramBody.getPosition().getY()) > 1.0E-006D))
      paramBody.setPosition((float)paramb.c(), (float)paramb.d());
    if (StrictMath.abs(paramb.e() - paramBody.getRotation()) > 1.0E-006D)
      paramBody.setRotation((float)paramb.e());
    if ((StrictMath.abs(paramb.f().x - paramBody.getVelocity().getX()) > 1.0E-006D) || (StrictMath.abs(paramb.f().y - paramBody.getVelocity().getY()) > 1.0E-006D))
      paramBody.adjustVelocity(new Vector2f((float)paramb.f().x - paramBody.getVelocity().getX(), (float)paramb.f().y - paramBody.getVelocity().getY()));
    if (StrictMath.abs(paramb.g() - paramBody.getAngularVelocity()) > 1.0E-006D)
      paramBody.adjustAngularVelocity((float)paramb.g() - paramBody.getAngularVelocity());
    if ((StrictMath.abs(paramb.h().x - paramBody.getForce().getX()) > 1.0E-006D) || (StrictMath.abs(paramb.h().y - paramBody.getForce().getY()) > 1.0E-006D))
      paramBody.setForce((float)paramb.h().x, (float)paramb.h().y);
    if (StrictMath.abs(paramb.i() - paramBody.getTorque()) > 1.0E-006D)
      paramBody.setTorque((float)paramb.i());
    if (StrictMath.abs(paramBody.getMass() - paramb.n()) > 1.0E-006D)
      throw new IllegalArgumentException("Can't update body mass.");
    if (StrictMath.abs(paramBody.getDamping() - paramb.j()) > 1.0E-006D)
      paramBody.setDamping((float)paramb.j());
    if (StrictMath.abs(paramBody.getRotDamping() - paramb.k()) > 1.0E-006D)
      paramBody.setRotDamping((float)paramb.k());
    if (StrictMath.abs(paramBody.getRestitution() - paramb.l()) > 1.0E-006D)
      paramBody.setRestitution((float)paramb.l());
    if (StrictMath.abs(paramBody.getFriction() - paramb.m()) > 1.0E-006D)
      paramBody.setFriction((float)paramb.m());
    if (!paramb.o().a(a(paramBody.getShape()), 1.0E-006D))
      paramBody.setShape(a(paramb.o()));
  }

  private static DynamicShape a(com.a.b.b.b paramb)
  {
    if (paramb == null)
      return null;
    Object localObject;
    if ((paramb instanceof a))
    {
      localObject = (a)paramb;
      return new Circle((float)((a)localObject).a());
    }
    if ((paramb instanceof com.a.b.b.c))
    {
      localObject = (com.a.b.b.c)paramb;
      return new Box((float)((com.a.b.b.c)localObject).a(), (float)((com.a.b.b.c)localObject).c());
    }
    if ((paramb instanceof d))
    {
      localObject = (d)paramb;
      return new Line((float)((d)localObject).a(), (float)((d)localObject).c(), (float)((d)localObject).d(), (float)((d)localObject).e());
    }
    throw new IllegalArgumentException("Unsupported form: " + paramb + '.');
  }

  private static com.a.b.b.b a(Shape paramShape)
  {
    if (paramShape == null)
      return null;
    Object localObject;
    if ((paramShape instanceof Circle))
    {
      localObject = (Circle)paramShape;
      return new a(((Circle)localObject).getRadius());
    }
    if ((paramShape instanceof Box))
    {
      localObject = (Box)paramShape;
      return new com.a.b.b.c(((Box)localObject).getSize().getX(), ((Box)localObject).getSize().getY());
    }
    if ((paramShape instanceof Line))
    {
      localObject = (Line)paramShape;
      return new d(((Line)localObject).getX1(), ((Line)localObject).getY1(), ((Line)localObject).getX2(), ((Line)localObject).getY2());
    }
    throw new IllegalArgumentException("Unsupported shape: " + paramShape + '.');
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.a.a.c
 * JD-Core Version:    0.6.2
 */