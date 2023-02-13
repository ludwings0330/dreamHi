import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import { ko } from 'date-fns/esm/locale';
import 'react-datepicker/dist/react-datepicker.css';

function CalendarAnnounce(props) {
  const [startDate, setStartDate] = useState(null);
  const [selectedDates, setSelectedDates] = useState([]);
  const filterPassedTime = (time) => {
    const currentDate = new Date();
    const selectedDate = new Date(time);

    return currentDate.getTime() < selectedDate.getTime();
  };

  const changeDate = (date) => {
    setStartDate(date);
  };

  const handleChange = (date) => {
    changeDate(date);
    if (startDate) {
      setStartDate(startDate.setHours(date.getHours(), date.getMinutes(), 0, 0));
      setSelectedDates([...selectedDates, startDate]);
      setStartDate(null);
    }
  };

  useEffect(() => {
    const possibleTimes = document.getElementsByClassName('react-datepicker__time-list-item');
    for (let possibleTime of possibleTimes) {
      possibleTime.addEventListener('click', (e) => {
        if (e.target.className.includes('react-datepicker__time-list-item')) {
          const selectedTime = new Date(e.target.getAttribute('data-time'));
          const selectedDate = new Date(selectedTime).setHours(0, 0, 0, 0);
          const existingDate = selectedDates.find(
            (d) => new Date(d).setHours(0, 0, 0, 0) === selectedDate,
          );
          if (!existingDate) {
            handleChange(selectedTime);
          } else {
            const newSelectedDates = selectedDates.filter((d) => d !== existingDate);
            setSelectedDates(newSelectedDates);
          }
        }
      });
    }
  }, []);

  const handleDelete = (date) => {
    setSelectedDates(selectedDates.filter((d) => d !== date));
  };

  return (
    <div>
      <DatePicker
        locale={ko}
        selected={null}
        onChange={handleChange}
        showTimeSelect
        timeFormat="HH:mm"
        timeIntervals={20}
        dateFormat="yy/MM/dd, aa hh:mm"
        timeCaption="시간"
        filterTime={filterPassedTime}
        minTime={new Date().setHours(9, 0, 0, 0)}
        maxTime={new Date().setHours(22, 0, 0, 0)}
        minDate={new Date()}
      />
      <div>
        {selectedDates.length > 0 &&
          selectedDates.map((date, index) => (
            <div key={index}>
              Selected Date:{' '}
              {date.toLocaleString('ko-KR', {
                year: '2-digit',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                hour12: true,
              })}
              <button onClick={() => handleDelete(date)}>X</button>
            </div>
          ))}
      </div>
    </div>
  );
}

export default CalendarAnnounce;
