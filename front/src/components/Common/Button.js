import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palette';

const StyledButton = styled.button`
  border: rgba(7, 7, 7, 0.16);
  border-radius: 5px;
  font-size: 1.25rem;
  font-weight: bold;
  padding: 0.5rem 1rem;
  margin-left: 20px;
  color: black;
  outline: #e8e8e8;
  cursor: pointer;
  background: white;
  opacity: 80%;

  &:hover {
    background: ${palette.dreamhi[0]};
  }
`;

const Button = props => <StyledButton {...props} />;

export default Button;