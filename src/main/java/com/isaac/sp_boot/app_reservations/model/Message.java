package com.isaac.sp_boot.app_reservations.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "messages")
public class Message implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 55035690986675832L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;

	@Column(length = 250)
    @NotEmpty(message = "El mensaje es obligatorio")
    @Size(max = 250, message = "El mensaje es demasiado grande")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "clientCabins")
    @JsonIgnoreProperties({ "reservations", "messages" })
    private Cabin cabin;

    @ManyToOne
    @JoinColumn(name = "clientMessages")
    @JsonIgnoreProperties({ "messages", "reservations" })
    private Client client;

    // Constructor

    /**
     * Constructor
     * 
     * @param messageText
     * @param client
     * @param cabin
     */
    public Message(String messageText, Client client, Cabin cabin) {
        this.messageText = messageText;
        this.client = client;
        this.cabin = cabin;
    }
}