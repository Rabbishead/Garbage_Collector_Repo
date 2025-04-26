<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="city_tileset" tilewidth="32" tileheight="32" spacing="8" margin="4" tilecount="42" columns="6">
 <image source="../images/citytiles.png" width="240" height="280"/>
 <tile id="15">
  <animation>
   <frame tileid="15" duration="360"/>
   <frame tileid="21" duration="360"/>
   <frame tileid="27" duration="360"/>
   <frame tileid="33" duration="360"/>
   <frame tileid="39" duration="360"/>
  </animation>
 </tile>
 <tile id="17">
  <properties>
   <property name="destination" value="PARK-SLUMS"/>
   <property name="name" value="SLUMS-PARK"/>
   <property name="orientation" value="d"/>
  </properties>
 </tile>
 <tile id="23">
  <properties>
   <property name="destination" value="SLUMS-PARK"/>
   <property name="name" value="PARK-SLUMS"/>
   <property name="orientation" value="a"/>
  </properties>
 </tile>
 <tile id="26">
  <properties>
   <property name="blocked" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="29">
  <properties>
   <property name="destination" value="RICH_DISTRICT-PARK"/>
   <property name="name" value="PARK-RICH_DISTRICT"/>
   <property name="orientation" value="d"/>
  </properties>
 </tile>
 <tile id="34">
  <properties>
   <property name="destination" value="REFLECTION_ARENA-RICH_DISTRICT"/>
   <property name="name" value="RICH_DISTRICT-REFLECTION_ARENA"/>
   <property name="orientation" value="s"/>
  </properties>
 </tile>
 <tile id="35">
  <properties>
   <property name="destination" value="PARK-RICH_DISTRICT"/>
   <property name="name" value="RICH_DISTRICT-PARK"/>
   <property name="orientation" value="a"/>
  </properties>
 </tile>
 <tile id="40">
  <properties>
   <property name="destination" value="RICH_DISTRICT-REFLECTION_ARENA"/>
   <property name="name" value="REFLECTION_ARENA-RICH_DISTRICT"/>
   <property name="orientation" value="w"/>
  </properties>
 </tile>
 <tile id="41">
  <properties>
   <property name="destination" value="CITY_ROOM_1_DOOR_3"/>
   <property name="name" value="CITY_ROOM_3_DOOR_2"/>
   <property name="orientation" value="a"/>
  </properties>
 </tile>
</tileset>
