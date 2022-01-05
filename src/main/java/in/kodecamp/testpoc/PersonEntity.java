package in.kodecamp.testpoc;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="TEST_PERSON")
public class PersonEntity implements Serializable{


	public PersonEntity() {

	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param createdAt
	 * @param modifiedAt
	 * @param version
	 */
	public PersonEntity(UUID id, String name, String address, LocalDateTime createdAt, LocalDateTime modifiedAt,
						Long version) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.version = version;
	}

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	private String address;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	private Long version;



	/**
	 * @return the id
	 */
	public UUID getId() { return id; }

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) { this.id = id; }

	/**
	 * @return the name
	 */
	public String getName() { return name; }

	/**
	 * @param name the name to set
	 */
	public void setName(String name) { this.name = name; }

	/**
	 * @return the address
	 */
	public String getAddress() { return address; }

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) { this.address = address; }

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() { return createdAt; }

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	/**
	 * @return the modifiedAt
	 */
	public LocalDateTime getModifiedAt() { return modifiedAt; }

	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(LocalDateTime modifiedAt) { this.modifiedAt = modifiedAt; }

	/**
	 * @return the version
	 */
	public Long getVersion() { return version; }

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) { this.version = version; }


	@Override
	public String toString() {
		return "PersonEntity [address=" + address + ", createdAt=" + createdAt + ", id=" + id + ", modifiedAt="
				+ modifiedAt + ", name=" + name + ", version=" + version + "]";
	}

	public PersonEntity name(String newName) {
		this.name = newName;
		return this;
	}

	public PersonEntity address(String addressNew) {
		this.address = addressNew;
		return this;
	}

	static public PersonEntity from(String name, String address) {
		PersonEntity pe = new PersonEntity();
		pe.setName(name);
		pe.setAddress(address);
		return pe;
	}

	static public PersonEntity from(UUID id ,String name, String address) {
		PersonEntity pe = new PersonEntity();
		pe.setId(id);
		pe.setName(name);
		pe.setAddress(address);
		return pe;
	}

	@PrePersist
	void prePersist() {
		this.createdAt = LocalDateTime.now();

	}

	@PreUpdate
	void preUpdate() {
		this.modifiedAt = LocalDateTime.now();
	}




}
