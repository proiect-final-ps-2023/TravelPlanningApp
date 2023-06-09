package com.travelPlanning.service.implementation;

import com.travelPlanning.model.Room;
import com.travelPlanning.repository.hospitality.RoomRepository;
import com.travelPlanning.service.RoomService;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void updateRoomNumber(Room room, String newNumber) throws BadAttributeValueExpException {
        Optional<Room> currentRoomOpt = roomRepository.findById(room.getId());
        if(currentRoomOpt.isPresent()){
            Room currentRoom = currentRoomOpt.get();
            currentRoom.setRoomNumber(newNumber);
            roomRepository.delete(room);
            roomRepository.save(currentRoom);
        } else {
            throw new BadAttributeValueExpException(room);
        }
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void updateRoom(Room room) {
        roomRepository.deleteById(room.getId());
        roomRepository.save(room);
    }
}
