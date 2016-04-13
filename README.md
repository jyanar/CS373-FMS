# CS373_Project1
Michael Gibbons Jorge Yanar

**System Name**: Facility Management System

**System Description**: Facilities are building units (buildings and rooms inside that a company uses to support the activities of the business. This system will support the management of buildings, their constant use, and maintenance spport when it is needed. The following are the three main functionalities of this system:
1. Facility and its description -- this will cover the functionalities such as adding a new facility to be managed; removing a facility from being managed; getting general information such as the number and capacity of facilities and their current statuses.
2. Facility Use - this covers the functionalities of managing the facility such as reserving a facility; the cost of using and maintaining it; assigning and de-assigning a facility for use.
3. Facility maintenance – this functionality covers the maintenance of a facility such as scheduling a facility for maintenance; checking maintenance status; listing maintenance requests; calculating down time of a facility and many more functionalities.

**Client Interfaces**:
- **Facility**:
	- [ ] `public object listFacilities()`
	- [ ] `public object getFacilityInformation()`
	- [ ] `public object requestAvailableCapacity()`
	- [ ] `public object addNewFacility()`
	- [ ] `public void addFacilityDetail()`
	- [ ] `public object removeFacility()`
- **Facility Use**:
	- [ ] `public object isInUseDuringInterval()`
	- [ ] `public object assignFacilityToUse()`
	- [ ] `public object vacateFacility()`
	- [ ] `public object listInspections()`
	- [ ] `public object listActualUsage()`
	- [ ] `public object calcUsageRate()`
- **Facility Maintenance**:
	- [ ] `public object makeFacilityMaintRequest()`
	- [ ] `public object scheduleMaintenance()`
	- [ ] `public object calcMaintenanceCostForFacility()`
	- [ ] `public object calcProblemRateForFacility()`
	- [ ] `public object calcDownTimeForFacility()`
	- [ ] `public object listMaintRequest()`
	- [ ] `public object listMaintenance()`
	- [ ] `public object listFacilityProblems()`
