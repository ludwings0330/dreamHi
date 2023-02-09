import React, { useState } from 'react';

const MultiSelect = () => {
    const options = ['Option 1', 'Option 2', 'Option 3'];
    const [selectedOptions, setSelectedOptions] = useState([]);

    const toggleOption = (option) => {
        if (selectedOptions.includes(option)) {
            setSelectedOptions(selectedOptions.filter((o) => o !== option));
        } else {
            setSelectedOptions([...selectedOptions, option]);
        }
    };

    return (
        <div>
            {options.map((option) => (
                <div key={option}>
                    <input
                        type="checkbox"
                        checked={selectedOptions.includes(option)}
                        onChange={() => toggleOption(option)}
                    />
                    {option}
                </div>
            ))}
        </div>
    );
};

export default MultiSelect;
