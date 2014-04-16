/*
 *	Drifting Souls 2
 *	Copyright (c) 2008 Christopher Jung
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.driftingsouls.ds2.server.modules.admin;

import net.driftingsouls.ds2.server.cargo.Cargo;
import net.driftingsouls.ds2.server.entities.User;
import net.driftingsouls.ds2.server.modules.admin.editoren.AbstractEditPlugin8;
import net.driftingsouls.ds2.server.modules.admin.editoren.EditorForm8;
import net.driftingsouls.ds2.server.ships.Ship;
import net.driftingsouls.ds2.server.ships.ShipType;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Aktualisierungstool fuer die Werte eines Schiffes.
 *
 * @author Sebastian Gift
 */
@AdminMenuEntry(category = "Schiffe", name = "Schiff")
public class EditShip extends AbstractEditPlugin8<Ship>
{
	public EditShip()
	{
		super(Ship.class);
	}

	@Override
	protected void configureFor(@Nonnull EditorForm8<Ship> form)
	{
		form.field("Name", String.class, Ship::getName, Ship::setName);
		form.field("Besitzer", User.class, Ship::getOwner, Ship::setOwner);
		form.field("System", Integer.class, Ship::getSystem, Ship::setSystem);
		form.field("x", Integer.class, Ship::getX, Ship::setX);
		form.field("y", Integer.class, Ship::getY, Ship::setY);
		form.field("Schiffstyp", ShipType.class, Ship::getBaseType, Ship::setBaseType);
		form.field("Hülle", Integer.class, Ship::getHull, Ship::setHull);
		form.field("Ablative Panzerung", Integer.class, Ship::getAblativeArmor, Ship::setAblativeArmor);
		form.field("Schilde", Integer.class, Ship::getShields, Ship::setShields);
		form.field("Crew", Integer.class, Ship::getCrew, Ship::setCrew);
		form.field("Nahrungsspeicher", Long.class, Ship::getNahrungCargo, Ship::setNahrungCargo);
		form.field("Energie", Integer.class, Ship::getEnergy, Ship::setEnergy);
		form.field("Sensoren", Integer.class, Ship::getSensors, Ship::setSensors);
		form.field("Antrieb", Integer.class, Ship::getEngine, Ship::setEngine);
		form.field("Kommunikation", Integer.class, Ship::getComm, Ship::setComm);
		form.field("Waffen", Integer.class, Ship::getWeapons, Ship::setWeapons);
		form.field("Hitze", Integer.class, Ship::getHeat, Ship::setHeat);

		Map<Integer, String> alarms = new HashMap<>();
		alarms.put(0, "Grün");
		alarms.put(1, "Gelb");
		alarms.put(2, "Rot");
		form.field("Alarm", Integer.class, Ship::getAlarm, Ship::setAlarm).withOptions(alarms);
		form.field("Statusflags", String.class, Ship::getStatus, Ship::setStatus);
		form.field("Cargo", Cargo.class, Ship::getCargo, Ship::setCargo);
	}
}
