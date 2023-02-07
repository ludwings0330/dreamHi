import React from 'react';
import ImageUpload from '../../imageup/ImageUpload';

import { useForm, Controller } from 'react-hook-form';
import ReactDatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import AnnouncementWriteImage from './AnnouncementWriteImage';

const defaultValues = {
  ReactDatepicker: new Date(),
};

const Input = ({ label, register, required }) => (
  <>
    <label>{label}</label>
    <input {...register(label, { required })} />
  </>
);

const Select = React.forwardRef(({ onChange, name, label }, ref) => (
  <>
    <label>{label}</label>
    <select name={name} ref={ref} onChange={onChange}>
      <option value="baby">~</option>
      <option value="10">10</option>
      <option value="15">15</option>
      <option value="10">20</option>
      <option value="10">25</option>
      <option value="10">30</option>
      <option value="10">35</option>
      <option value="10">40</option>
      <option value="10">45</option>
      <option value="10">50</option>
      <option value="10">60</option>
      <option value="10">70</option>
    </select>
  </>
));

function AnnouncementWriteItem(props) {
  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    alert(JSON.stringify(data));
  };
  const { control } = useForm({ defaultValues });

  return (
    <div>
      <h2>공고 리스트 작성 페이지</h2>

      <form onSubmit={handleSubmit(onSubmit)}>
        <Input label="작품명" register={register} required />
        <Input label="제작사" register={register} required />
        <Input label="출연료" register={register} required />
        <Input label="촬영 기간" register={register} required />
        <Select label="나이" {...register('Age')} />
        <span>~</span>
        <Select {...register('Age')} />
        <br></br>
        <Select label="키" {...register('Age')} />
        <span>~</span>
        <Select {...register('Age')} />
        <section>
          <label>공고 마감일</label>
          <Controller
            control={control}
            name="ReactDatepicker"
            render={({ field }) => (
              <ReactDatePicker
                className="input"
                placeholderText="Select date"
                onChange={(e) => field.onChange(e)}
                selected={field.value}
              />
            )}
          />
        </section>
      </form>
      <AnnouncementWriteImage />
    </div>
  );
}

export default AnnouncementWriteItem;