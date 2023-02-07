// import React, { useState } from 'react';
// import axios from 'axios';
//
// const handleSubmit = async (event) => {
//     event.preventDefault();
//
//     const data = {
//         title: "title",
//         producerId: value,
//         payment: "value",
//         crankPeriod: "value",
//         endDate: "2023-01-27T10:00:00",
//         description: "value",
//         pictureUrl: "url",
//         castings: castingsArray
//     };
//
//     try {
//         const response = await axios.post("your-api-endpoint", data, {
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         });
//
//         console.log(response.data);
//     } catch (error) {
//         console.error(error);
//     }
// };
//
// const Form = () => {
//     const [castingsArray, setCastingsArray] = useState([
//         {
//             name: "value",
//             description: "value",
//             headcount: 1,
//             minHeight: 130,
//             maxHeight: 150,
//             minAge: 5,
//             maxAge: 10,
//             gender: "MALE",
//             styles: [ num1, num2, ... ]
//         }
//     ]);
//
//     const [casting, setCasting] = useState({
//         name: "",
//         description: "",
//         headcount: 1,
//         minHeight: 130,
//         maxHeight: 150,
//         minAge: 5,
//         maxAge: 10,
//         gender: "MALE",
//         styles: [ num1, num2, ... ]
//     });
//
//     const handleCastingChange = (event) => {
//         setCasting({ ...casting, [event.target.name]: event.target.value });
//     };
//
//     const addCasting = () => {
//         setCastingsArray([...castingsArray, casting]);
//         setCasting({
//             name: "",
//             description: "",
//             headcount: 1,
//             minHeight: 130,
//             maxHeight: 150,
//             minAge: 5,
//             maxAge: 10,
//             gender: "MALE",
//             styles: [ num1, num2, ... ]
//         });
//     };
//
//     return (
//         <form onSubmit={handleSubmit}>
//             <input type="text" name="name" value={casting.name} onChange={handleCastingChange} />
//             <input type="text" name="description" value={casting.description} onChange={handleCastingChange} />
//             {/* Other input fields */}
//             <button type="button" onClick={addCasting}>Add Casting</button>
//             <button type="submit">Submit</button>
//         </form>
//     );
// };
//
// export default Form;




import React from 'react';
import axios from 'axios';
import { useRecoilState } from 'recoil';
import { castingState } from './atoms';

const handleSubmit = async (event) => {
    event.preventDefault();

    const data = {
        title: "title",
        producerId: value,
        payment: "value",
        crankPeriod: "value",
        endDate: "2023-01-27T10:00:00",
        description: "value",
        pictureUrl: "url",
        castings: castingsArray
    };

    try {
        const response = await axios.post("your-api-endpoint", data, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

const Form = () => {
    const [castingsArray, setCastingsArray] = useRecoilState(castingState);
    const [casting, setCasting] = useState({
        name: "",
        description: "",
        headcount: 1,
        minHeight: 130,
        maxHeight: 150,
        minAge: 5,
        maxAge: 10,
        gender: "MALE",
        styles: [ num1, num2, ... ]
    });

    const handleCastingChange = (event) => {
        setCasting({ ...casting, [event.target.name]: event.target.value });
    };

    const addCasting = () => {
        setCastingsArray([...castingsArray, casting]);
        setCasting({
            name: "",
            description: "",
            headcount: 1,
            minHeight: 130,
            maxHeight: 150,
            minAge: 5,
            maxAge: 10,
            gender: "MALE",
            styles: [ num1, num2, ... ]
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="name" value={casting.name} onChange={handleCastingChange} />
            <input type="text" name="description" value={casting.description} onChange={handleCastingChange} />
            {/* Other input fields */}
            <button type="button" onClick={addCasting}>Add Casting</button>
            <button type="submit">Submit</button>
        </form>
    );
};

export default Form;
